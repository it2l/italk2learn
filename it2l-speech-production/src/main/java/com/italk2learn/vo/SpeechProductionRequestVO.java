package com.italk2learn.vo;

public class SpeechProductionRequestVO extends RequestVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ENGLISH="en";
	public static final String GERMAN="de";
	
	
	private String message;
	private String language;
	private boolean voiceType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isVoiceType() {
		return voiceType;
	}

	public void setVoiceType(boolean voiceType) {
		this.voiceType = voiceType;
	}

}
