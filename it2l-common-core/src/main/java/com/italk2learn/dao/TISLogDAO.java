package com.italk2learn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hibernate.dto.Tislog;
import com.hibernate.dto.User;
import com.italk2learn.dao.inter.ITISLogDAO;
import com.italk2learn.exception.ITalk2LearnException;


@Repository
public class TISLogDAO extends HibernateDaoSupport implements ITISLogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getITalk2LearnSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean storeDataTIS(int idUser, String key, String value) throws ITalk2LearnException {
		final Session session = this.getITalk2LearnSession();
		try{
			Tislog tis=new Tislog();
			User us= (User) session.load(User.class, idUser);
			tis.setUser(us);
			tis.setTiskey(key);
			tis.setTisvalue(value);
			session.saveOrUpdate(tis);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			throw new ITalk2LearnException(e);
		}
	}
	

}