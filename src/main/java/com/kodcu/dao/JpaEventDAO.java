package com.kodcu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import com.kodcu.dao.interfaces.EventDao;
import com.kodcu.entity.Event;

/**
 * @author kodcu
 *
 */
@Transactional
public class JpaEventDAO extends CommonDao<Event> implements EventDao {

	/**
	 * @param clazz
	 */
	public JpaEventDAO() {
		super(Event.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kodcu.dao.interfaces.Dao#countAll()
	 */
	public int countAll() {
		return (getJpaTemplate().execute(new JpaCallback<Long>() {

			public Long doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<Long> query = em.createQuery("select count(e) from Event e", Long.class);
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
	public List<Event> findAll() {
		return getJpaTemplate().find("select e from Event e");
	}

}
