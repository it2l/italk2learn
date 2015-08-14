package com.italk2learn.bo;

import java.io.File;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.italk2learn.bo.inter.ISpeechProductionBO;
import com.italk2learn.util.MessagesConverter;
import com.italk2learn.vo.SpeechProductionRequestVO;
import com.italk2learn.vo.SpeechProductionResponseVO;

@Service("speechProductionBO")
public class SpeechProductionBO implements ISpeechProductionBO  {
	

	private static final Logger logger = LoggerFactory.getLogger(SpeechProductionBO.class);
	
	public static final String ENGLISH_MALE= "dfki-spike-hsmm";
	public static final String ENGLISH_FEMALE= "cmu-slt-hsmm";
	public static final String GERMAN_MALE= "dfki-pavoque-neutral-hsmm";
	public static final String GERMAN_FEMALE= "bits1-hsmm";
	
	
    public SpeechProductionResponseVO generateAudioFile(SpeechProductionRequestVO request) {
    	SpeechProductionResponseVO response= new SpeechProductionResponseVO();
    	MessagesConverter mc = new MessagesConverter(); 
		try {
			Iterator<Entry<String, String>> it = mc.getMessages().entrySet().iterator();
			while (it.hasNext()) {
			    Map.Entry entry = (Map.Entry) it.next();
			    String key = (String)entry.getKey();
			    if (key.equals(MessagesConverter.normaliseString(request.getMessage()))){
			    	String value = (String)entry.getValue();
			    	request.setMessage(value);
			    }
			}
			if (mc.containFraction(MessagesConverter.normaliseString(request.getMessage()))){
				if (request.getLanguage().contains(SpeechProductionRequestVO.ENGLISH)){
					request.setMessage(MessagesConverter.transformFractionsInString(MessagesConverter.normaliseString(request.getMessage()), MessagesConverter.Language.EN));
				} else if (request.getLanguage().contains(SpeechProductionRequestVO.GERMAN)) {
					request.setMessage(MessagesConverter.transformFractionsInString(MessagesConverter.normaliseString(request.getMessage()), MessagesConverter.Language.DE));
				}
			}
			ResourceBundle rb= ResourceBundle.getBundle("speechproduction-config");
			String _PATH=rb.getString("sp.path");
			String finalName=request.getMessage()+request.getLanguage()+request.isVoiceType();
			if (!existFile(_PATH+finalName.hashCode()+".wav")){
				MaryInterface marytts = new LocalMaryInterface();
				Set<String> voices = marytts.getAvailableVoices();
				logger.info("I currently have " + marytts.getAvailableVoices() + " voices in "
					    + marytts.getAvailableLocales() + " languages available.");
				logger.info("Out of these, " + marytts.getAvailableVoices(Locale.GERMAN) + " are for German.");
				logger.info("Out of these, " + marytts.getAvailableVoices(Locale.ENGLISH) + " are for English.");
				if (request.getLanguage().contains(SpeechProductionRequestVO.ENGLISH)){
					if (request.isVoiceType()==true){
						//JLF:Male voice
						marytts.setVoice(ENGLISH_MALE);
					}
					else {
						//JLF:Female voice
						marytts.setVoice(ENGLISH_FEMALE);
					}
				}
				else if (request.getLanguage().contains(SpeechProductionRequestVO.GERMAN)) {
					if (request.isVoiceType()==true){
						//JLF:Male voice
						marytts.setVoice(GERMAN_MALE);
					}
					else {
						//JLF:Female voice
						marytts.setVoice(GERMAN_FEMALE);
					}
				} else {
					marytts.setVoice(ENGLISH_FEMALE);
				}
				AudioInputStream audio = marytts.generateAudio(request.getMessage());
				AudioFormat audioFormat = audio.getFormat();
				logger.info("Channels: "+audioFormat.getChannels());
				logger.info("Encoding: "+audioFormat.getEncoding());
				logger.info("Frame Rate: "+audioFormat.getFrameRate());
				logger.info("Frame Size: "+audioFormat.getFrameSize());
				logger.info("Sample Rate: "+audioFormat.getSampleRate());
				logger.info("Sample size (bits): "+audioFormat.getSampleSizeInBits());
				logger.info("Big endian: "+audioFormat.isBigEndian());
				logger.info("Audio Format String: "+audioFormat.toString());
		        AudioInputStream encodedASI = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, audio);
	
		        try {
		            int i = AudioSystem.write(encodedASI, AudioFileFormat.Type.WAVE, new File(_PATH+finalName.hashCode()+".wav"));
		            logger.info("Bytes Written: "+i);
		        } catch(Exception e){
		            e.printStackTrace();
		        }
	        }
	        response.setFile(finalName.hashCode()+".wav");
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
    	return response;
    }
    
    public boolean existFile(String path) {
    	File f = new File(path);
    	if(f.exists() && !f.isDirectory()) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }


}
