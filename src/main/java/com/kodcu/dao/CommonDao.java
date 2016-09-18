package com.kodcu.dao;

import java.io.Serializable;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.kodcu.dao.interfaces.Dao;
import com.kodcu.entity.DomainObject;

/**
 * @author kodcu
 * 
 */
@Transactional
public abstract class CommonDao<T extends DomainObject> extends JpaDaoSupport implements Dao<T> {

	private final Class<T> clazz;
	

	public CommonDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.kodcu.dao.interfaces.Dao#delete(com.kodcu.entity.DomainObject)
	 */
	public void delete(T o) {


		getJpaTemplate().remove(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kodcu.dao.interfaces.Dao#load(java.io.Serializable)
	 */
	public T load(Serializable id) {
		return getJpaTemplate().find(clazz, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.kodcu.dao.interfaces.Dao#save(com.kodcu.entity.DomainObject)
	 */
	public T save(T o) {
		return getJpaTemplate().merge(o);
	}

}
