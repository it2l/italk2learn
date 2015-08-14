package com.italk2learn.dao.inter;

import com.hibernate.dto.Studentmodel;
import com.italk2learn.exception.ITalk2LearnException;

public interface IStudentModelDAO {
	
	public boolean insertCurrentStudentModelByUser(int idUser, boolean isExploratoryExercise, int studentChallenge, String currentExercise, int unstructuredCounter, int structuredCounter, String lastExploratoryExercise) throws ITalk2LearnException;

	public Studentmodel getCurrentStudentModelByUser(int idUser) throws ITalk2LearnException;

}
