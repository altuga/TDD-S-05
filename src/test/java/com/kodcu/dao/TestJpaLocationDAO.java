package com.kodcu.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kodcu.dao.interfaces.LocationDao;
import com.kodcu.entity.Location;

/**
 * @author kodcu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestJpaLocationDAO {

	@Autowired
	private LocationDao locationDao;

	/**
	 * Test method for {@link com.kodcu.dao.JdbcEventDAO#countAll()}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testCountAll() {
		assertEquals(0, locationDao.countAll());

		testSave();

		assertEquals(1, locationDao.countAll());
	}

	/**
	 * Test method for
	 * {@link com.kodcu.dao.JdbcEventDAO#delete(com.kodcu.entity.Event)}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testDelete() {

		Location location = new Location();
		location.setAddress("address");
		location.setName("name");

		location = locationDao.save(location);

		assertEquals(1, locationDao.countAll());

		locationDao.delete(location);

		assertEquals(0, locationDao.countAll());
	}

	/**
	 * Test method for {@link com.kodcu.dao.JdbcEventDAO#findAll()}.
	 */
	@Test
	@Transactional
	@Rollback
	public void testFindAll() {
		testSave();

		List<Location> events = locationDao.findAll();

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
		Location location = new Location();
		location.setAddress("address");
		location.setName("name");

		location = locationDao.save(location);

		assertNotNull(location.getId());
		assertEquals(1, locationDao.countAll());

		Location newLocation = locationDao.load(location.getId());

		assertEquals(location, newLocation);

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

		assertNull(location.getId());

		location = locationDao.save(location);

		assertNotNull(location.getId());

	}

}
