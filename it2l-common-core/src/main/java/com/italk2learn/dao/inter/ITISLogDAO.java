package com.italk2learn.dao.inter;

import com.italk2learn.exception.ITalk2LearnException;

public interface ITISLogDAO {
	
	public boolean storeDataTIS(int idUser, String key, String value) throws ITalk2LearnException;

}
