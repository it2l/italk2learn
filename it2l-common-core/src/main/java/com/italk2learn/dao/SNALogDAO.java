package com.italk2learn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hibernate.dto.Snalog;
import com.hibernate.dto.User;
import com.italk2learn.dao.inter.ISNALogDAO;
import com.italk2learn.exception.ITalk2LearnException;


@Repository
public class SNALogDAO extends HibernateDaoSupport implements ISNALogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getITalk2LearnSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean storeDataSNA(int idUser, String key, String value) throws ITalk2LearnException {
		final Session session = this.getITalk2LearnSession();
		try{
			Snalog sna=new Snalog();
			User us= (User) session.load(User.class, idUser);
			sna.setUser(us);
			sna.setSnakey(key);
			sna.setSnavalue(value);
			session.saveOrUpdate(sna);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			throw new ITalk2LearnException(e);
		}
	}
	

}