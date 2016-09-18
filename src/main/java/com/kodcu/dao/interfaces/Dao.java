package com.kodcu.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.kodcu.entity.DomainObject;

/**
 * @author kodcu
 * 
 */
public interface Dao<T extends DomainObject> {
	public void delete(T o);

	public T load(Serializable id);

	public T save(T o);

	public List<T> findAll();

	public int countAll();
}
