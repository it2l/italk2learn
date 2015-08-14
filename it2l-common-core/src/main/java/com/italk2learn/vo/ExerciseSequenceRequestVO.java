package com.italk2learn.vo;

public class ExerciseSequenceRequestVO extends RequestVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idUser;
	private int idExercise;
	private int idNextexercise;
	private String idVPSExercise;
	private String feedback;
	private String user;
	private String[] sequence;
	private String nameExercise;
	private int condition;
	
	
	public String getNameExercise() {
		return nameExercise;
	}
	public void setNameExercise(String nameExercise) {
		this.nameExercise = nameExercise;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getIdExercise() {
		return idExercise;
	}
	public void setIdExercise(int idExercise) {
		this.idExercise = idExercise;
	}
	public int getIdNextexercise() {
		return idNextexercise;
	}
	public void setIdNextexercise(int idNextexercise) {
		this.idNextexercise = idNextexercise;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String[] getSequence() {
		return sequence;
	}
	public void setSequence(String[] sequence) {
		this.sequence = sequence;
	}
	public String getIdVPSExercise() {
		return idVPSExercise;
	}
	public void setIdVPSExercise(String idVPSExercise) {
		this.idVPSExercise = idVPSExercise;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}

}
