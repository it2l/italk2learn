package com.italk2learn.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.italk2learn.bo.inter.ISpeechProductionBO;
import com.italk2learn.vo.SpeechProductionRequestVO;
import com.italk2learn.vo.SpeechProductionResponseVO;

/**
 * JLF: Handles requests for the application speech recognition.
 */
@Controller
@Scope("session")
@RequestMapping("/speechProduction")
public class SpeechProductionController{
	

	private static final Logger logger = LoggerFactory.getLogger(SpeechProductionController.class);
	
	private ISpeechProductionBO speechProductionBO;
	

	@Autowired
    public SpeechProductionController(ISpeechProductionBO speechProductionBO) {
		this.speechProductionBO=speechProductionBO;
    }
	
	
	/**
	 * Method that creates speech production
	 */
	@RequestMapping(value = "/generateAudioFile",method = RequestMethod.POST)
	@ResponseBody
	public String generateAudioFile(@RequestBody SpeechProductionRequestVO data, HttpServletRequest req) {
		logger.info("JLF --- SpeechProductionController generateAudioFile --- Message= "+data.getMessage());
		try {
			SpeechProductionResponseVO response=getSpeechProductionBO().generateAudioFile(data);
			return response.getFile();
		} catch (Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	/**
	 * Method that creates speech production
	 */
	@RequestMapping(value = "/getHash",method = RequestMethod.POST)
	@ResponseBody
	public int getHash(@RequestBody SpeechProductionRequestVO data, HttpServletRequest req) {
		logger.info("JLF --- SpeechProductionController getHash");
			return data.getMessage().hashCode();
	}


	public ISpeechProductionBO getSpeechProductionBO() {
		return speechProductionBO;
	}


	public void setSpeechProductionBO(ISpeechProductionBO speechProductionBO) {
		this.speechProductionBO = speechProductionBO;
	}
	
	

}
