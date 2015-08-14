package com.italk2learn.util;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateWavFileUtil {

	private ArrayList<byte[]> chunkList;
	private ArrayList<Integer> lenghtsChunks;
	private int numberOfChunks;
	// mono: 1
	// stereo: 2
	private int monoStereo = 1;
//	private int monoStereo = 2;
	
	public CreateWavFileUtil() {
		chunkList = new ArrayList<byte[]>();
		lenghtsChunks = new ArrayList<Integer>();
		numberOfChunks = 0;
	}
	
	public void addChunk(byte[] chunk) {
		chunkList.add(chunk);
		lenghtsChunks.add(new Integer(chunk.length));
		numberOfChunks++;
	}
	
	public String createWavFile(int numChunks, String user, int counter, String path) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String name = dateFormat.format(date)+"-"+user+"-"+counter+".wav";
		int length = 0;
		ArrayList<byte[]> help = new ArrayList<byte[]>(numChunks);
		ArrayList<byte[]> help2 = new ArrayList<byte[]>(numChunks);
		
		try {
		
			for (int i = (numberOfChunks-1); i >= (numberOfChunks-numChunks); i--) {
				length = length + lenghtsChunks.get(i);
				help2.add(chunkList.get(i));
			}
			for (int i = 0; i < numChunks; i++) {
				help.add(help2.get((numChunks-1)-i));
			}
			byte[] data;
			
			if (monoStereo == 1) {
				data = new byte[length*2];
				int idx = -1;
				for (int i = 0; i < numChunks; i++) {
					for (int j = 0; j < help.get(i).length/2; j =j+1) {
						idx++;
						data[idx] = help.get(i)[j*2];
						idx++;
						data[idx] = help.get(i)[(j*2)+1];
						idx++;
						data[idx] = help.get(i)[j*2];
						idx++;
						data[idx] = help.get(i)[(j*2)+1];
					}
				}
				length = length *2;
			} else {
				data = new byte[length];
				int idx = -1;
				for (int i = 0; i < numChunks; i++) {
					for (int j = 0; j < help.get(i).length; j++) {
						idx++;
						data[idx] = help.get(i)[j];
					}
				}
			}
	
			DataOutputStream outFile = new DataOutputStream(new FileOutputStream(path+name));
			outFile.writeBytes("RIFF"); // byte 0-3
			outFile.write(intToByteArray(length+36), 0, 4); // byte 4 - 7 how big is the rest of this file?
			outFile.writeBytes("WAVE"); // byte 8 - 11 
			outFile.writeBytes("fmt "); // byte 12 - 15
			outFile.write(intToByteArray(16), 0, 4); // byte 16 - 19 size of this chunk
			outFile.write(intToByteArray(1), 0, 2); // byte 20 - 21 what is the audio format? 1 for PCM = Pulse Code Modulation
			outFile.write(intToByteArray(2), 0, 2); // byte 22 - 23 mono or stereo? 1 or 2?
            outFile.write(intToByteArray(44100), 0, 4); // byte 24 - 27 samples per second (numbers per second)
            outFile.write(intToByteArray(176400), 0, 4); // byte 28 - 31 bytes per second 
            outFile.write(intToByteArray(4), 0, 2); // byte 32 - 33 # of bytes in one sample, for all channels
            outFile.write(intToByteArray(16), 0, 2); // byte 34 - 35 how many bits in a sample(number)?  usually 16 or 24
            outFile.writeBytes("data"); // byte 36 - 39 data
            outFile.write(intToByteArray(length), 0, 4); // 40 - 43 how big is this data chunk
            outFile.write(data);
			
			outFile.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return name;
	}
    
    private static byte[] intToByteArray(int a)
	{
	    byte[] chunk = new byte[4];
	    chunk[0] = (byte) (a & 0xFF);   
	    chunk[1] = (byte) ((a >> 8) & 0xFF);   
	    chunk[2] = (byte) ((a >> 16) & 0xFF);   
	    chunk[3] = (byte) ((a >> 24) & 0xFF);
	    return chunk;
	}

    private static int byteArrayToInt(byte[] b) 
	{
    	return   b[0] & 0xFF |
	            (b[1] & 0xFF) << 8 |
	            (b[2] & 0xFF) << 16 |
	            (b[3] & 0xFF) << 24;
	}

	/**
	 * @return the chunkList
	 */
	public ArrayList<byte[]> getChunkList() {
		return chunkList;
	}

	/**
	 * @param chunkList the chunkList to set
	 */
	public void setChunkList(ArrayList<byte[]> chunkList) {
		this.chunkList = chunkList;
	}

	/**
	 * @return the monoStereo
	 */
	public int getMonoStereo() {
		return monoStereo;
	}

	/**
	 * @param monoStereo the monoStereo to set
	 */
	public void setMonoStereo(int monoStereo) {
		this.monoStereo = monoStereo;
	}

}
