package com.kodcu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import com.kodcu.dao.interfaces.LocationDao;
import com.kodcu.entity.Location;

/**
 * @author kodcu
 *
 */
@Transactional
public class JpaLocationDAO extends CommonDao<Location> implements LocationDao {

	/**
	 * @param clazz
	 */
	public JpaLocationDAO() {
		super(Location.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kodcu.dao.interfaces.Dao#countAll()
	 */
	public int countAll() {
		return ( getJpaTemplate().execute(new JpaCallback<Long>() {

			public Long doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<Long> query = em.createQuery("select count(l) from Location l", Long.class);
				return query.getSingleResult();
			}
		})).intValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kodcu.dao.interfaces.Dao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Location> findAll() {
		return getJpaTemplate().find("select l from Location l");
	}

}
