package com.italk2learn.util;

import java.util.ResourceBundle;

public class WhizzUtil {
	
	private static final int TOTAL=100;
	private static int NUM_QUESTIONS=5;
	
	public static int computeScore(int score){
		ResourceBundle rb= ResourceBundle.getBundle("italk2learn-config");
		NUM_QUESTIONS = Integer.parseInt(rb.getString("questions"));
		return score *(TOTAL/NUM_QUESTIONS);
	}
	
	public static String marshallWhizz(String exercise){
		exercise= exercise.substring(7, exercise.length());
		exercise="GB"+exercise;
		return exercise;
	}
	
	public static String unmarshallWhizz(String exercise){
		//MA_GBR_1075CAx0200
		//GB0900CAx0200
		exercise= exercise.substring(2, exercise.length());
		exercise="MA_GBR_"+exercise;
		return exercise;
	}

}
