package com.italk2learn.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hibernate.dto.Studentmodel;
import com.hibernate.dto.User;
import com.italk2learn.dao.inter.IStudentModelDAO;
import com.italk2learn.exception.ITalk2LearnException;


@Repository
public class StudentModelDAO extends HibernateDaoSupport implements IStudentModelDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getITalk2LearnSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean insertCurrentStudentModelByUser(int idUser, boolean isExploratoryExercise, int studentChallenge, String currentExercise, int unstructuredCounter, int structuredCounter, String lastExploratoryExercise) throws ITalk2LearnException {
		final Session session = this.getITalk2LearnSession();
		byte b;
		try {
			if (isExploratoryExercise)
				b=1;
			else
				b=0;
			final Criteria criteria = getITalk2LearnSession().createCriteria(Studentmodel.class);
			criteria.createCriteria("user", "u");
			criteria.add(Restrictions.eq("u.idUser", idUser));
			//JLF 
			if ((Studentmodel) criteria.uniqueResult()!=null) {
				Studentmodel stc= (Studentmodel) criteria.uniqueResult();
				stc.setIsExploratoryExercise(b);
				stc.setStudentChallenge(studentChallenge);
				stc.setCurrentExercise(currentExercise);
				stc.setUnstructuredCounter(unstructuredCounter);
				stc.setStructuredCounter(structuredCounter);
				stc.setLastExploratoryExercise(lastExploratoryExercise);
				session.saveOrUpdate(stc);
				return true;
			} else {
				Studentmodel st=new Studentmodel();
				User us= (User) session.load(User.class, idUser);
				st.setUser(us);
				st.setIsExploratoryExercise(b);
				st.setStudentChallenge(studentChallenge);
				st.setCurrentExercise(currentExercise);
				st.setUnstructuredCounter(unstructuredCounter);
				st.setStructuredCounter(structuredCounter);
				st.setLastExploratoryExercise(lastExploratoryExercise);
				session.saveOrUpdate(st);
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
			throw new ITalk2LearnException(e);
		}
	}
	
	public Studentmodel getCurrentStudentModelByUser(int idUser) throws ITalk2LearnException {
		try {
			final Criteria criteria = getITalk2LearnSession().createCriteria(Studentmodel.class);
			criteria.createCriteria("user", "u");
			criteria.add(Restrictions.eq("u.idUser", idUser));
			return (Studentmodel) criteria.uniqueResult();
		} catch (Exception e){
			e.printStackTrace();
			throw new ITalk2LearnException(e);
		}
	}
	

}