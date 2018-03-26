package com.techelevator.projects.model.jdbcs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Campground;

public class CampgroundJdbcDAOTest {
	private static SingleConnectionDataSource dataSource;
	private CampgroundJdbcDAO sut;
	private Long newParkId;
	private Long newCampgroundId;
	private Long newCampsiteId;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");

		dataSource.setAutoCommit(false);
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataSource.destroy();
	}


	@Before
	public void setUp() throws Exception {
		sut = new CampgroundJdbcDAO(dataSource);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String newPark = "INSERT INTO park (name, location, establish_date, area, visitors, description) VALUES (?, ?, ?, ?, ?, ?) RETURNING park_id";
		newParkId = jdbcTemplate.queryForObject(newPark, Long.class,  "TEST", "LOCATION", LocalDate.parse("1900-01-01"), 234, 100, "Because nature.");
		String newCampground = "INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (?, ?, ?, ?, ?) RETURNING campground_id";
		newCampgroundId = jdbcTemplate.queryForObject(newCampground, Long.class,  newParkId, "TESTCAMPGROUND", "01", "04", new BigDecimal("35.00"));
		String newCampsite = "INSERT INTO site (campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) VALUES (?, ?, ?, ?, ?, ?) RETURNING site_id";
		newCampsiteId = jdbcTemplate.queryForObject(newCampsite, Long.class,  newCampgroundId, 1, 5, false, 0, false);
	
	
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}



	@Test
	public void testGetCampgroundsByParkId() {
		List<Campground> allCampgrounds = sut.getCampgroundsByParkId(newParkId);
		Campground campground = allCampgrounds.get(0);
		assertNotNull(campground);
		assertEquals(newCampgroundId, campground.getId());
	
		
	}
	
	
	
	
}
