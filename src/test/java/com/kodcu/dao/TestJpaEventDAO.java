package com.kodcu.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kodcu.dao.interfaces.EventDao;
import com.kodcu.entity.Event;
import com.kodcu.entity.Location;

/**
 * @author kodcu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestJpaEventDAO {

	@Autowired
	private EventDao eventDao;

	/**
	 * Test method for {@link com.kodcu.dao.JdbcEventDAO#countAll()}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testCountAll() {
		assertEquals(0, eventDao.countAll());

		testSave();

		assertEquals(1, eventDao.countAll());
	}

	/**
	 * Test method for
	 * {@link com.kodcu.dao.JdbcEventDAO#delete(com.kodcu.entity.Event)}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testDelete() {

		Event event = new Event();
		event.setName("event name");
		event.setDescription("description");
		event.setDate(new Timestamp(System.currentTimeMillis()));

		event = eventDao.save(event);

		assertEquals(1, eventDao.countAll());

		eventDao.delete(event);

		assertEquals(0, eventDao.countAll());
	}

	/**
	 * Test method for {@link com.kodcu.dao.JdbcEventDAO#findAll()}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testFindAll() {
		testSave();

		List<Event> events = eventDao.findAll();

		assertEquals(1, events.size());
	}

	/**
	 * Test method for
	 * {@link com.kodcu.dao.JdbcEventDAO#load(java.io.Serializable)}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testLoad() {
		Event event = new Event();
		event.setName("event name");
		event.setDescription("description");
		event.setDate(new Timestamp(System.currentTimeMillis()));

		event = eventDao.save(event);

		assertNotNull(event.getId());
		assertEquals(1, eventDao.countAll());

		Event newEvent = eventDao.load(event.getId());

		assertEquals(event, newEvent);

	}

	/**
	 * Test method for
	 * {@link com.kodcu.dao.JdbcEventDAO#save(com.kodcu.entity.Event)}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testSave() {

		Location location = new Location();
		location.setAddress("1 high street");
		location.setName("location");

		Event event = new Event();
		event.setName("event name");
		event.setDescription("description");
		event.setLocation(location);
		event.setDate(new Timestamp(System.currentTimeMillis()));

		assertNull(event.getId());

		event = eventDao.save(event);

		assertNotNull(event.getId());

	}

}
