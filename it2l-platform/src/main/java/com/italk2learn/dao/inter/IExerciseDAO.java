package com.italk2learn.dao.inter;

import java.util.List;

import com.hibernate.dto.Exercises;
import com.italk2learn.exception.ITalk2LearnException;
import com.italk2learn.vo.ExerciseVO;

public interface IExerciseDAO {
	
	public List<Exercises> getSequenceExercises(int idUser) throws Exception;
	
	public Exercises getSpecificExercise(int idExercise) throws Exception;
	
	public ExerciseVO getNextExercise(int idUser, int idExercise) throws Exception;
	
	public ExerciseVO getWholeViewFromIDSequencer(int idUser, String idSequencer) throws Exception;
	
	public ExerciseVO getBackExercise(int idUser, int idExercise) throws Exception;
	
	public void setNextExercise(int idUser, int idExercise, int idNextexercise, String feedback) throws Exception;
	
	public void insertCurrentExercise(int idUser, int idView) throws ITalk2LearnException;
	
	public void insertCurrentVPSExercise(int idUser, String idSequencerView, int idExercise) throws ITalk2LearnException;
	
	public void insertLastScore(int idUser, int lastScore) throws ITalk2LearnException;
	
	public Exercises getFirstExercise(int idUser) throws Exception;
	
	public Exercises getIDSequencer(int idExercise) throws ITalk2LearnException;
	
	public List<Exercises> getAllExercises() throws ITalk2LearnException;
	
	public Exercises getIDExerciseFromSequencer(String nameExercise) throws Exception;
	
	public void insertCondition(int idUser, int condition) throws ITalk2LearnException;

}
