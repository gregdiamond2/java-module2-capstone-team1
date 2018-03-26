package com.techelevator.projects.model.jdbcs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Park;

public class ParkJdbcDAOTest {
	private static SingleConnectionDataSource dataSource;
	private ParkJdbcDAO sut;
	private Long newParkId;
	
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
		sut = new ParkJdbcDAO(dataSource);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String newPark = "INSERT INTO park (name, location, establish_date, area, visitors, description) VALUES (?, ?, ?, ?, ?, ?) RETURNING park_id";
		newParkId = jdbcTemplate.queryForObject(newPark, Long.class,  "TEST", "LOCATION", LocalDate.parse("1900-01-01"), Long.parseLong("234"), Long.parseLong("100"), "Because nature.");
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}
	@Test
	public void testGetAllParks() {
		List<Park> allParks = sut.getAllParks();

		for (Park park : allParks) {
			if (park.getName().equals("TEST") && park.getId().equals(newParkId)) {
				assertEquals(newParkId, park.getId());
				assertEquals("TEST", park.getName());
				return;
			}
		}
		fail("Test Park not found");
	}
	

	
	@Test
	public void testGetParkById() {
		Park park = sut.getParkById(newParkId);
		assertEquals("TEST", park.getName());
		assertEquals(newParkId, park.getId());
		
	}

	
	
	

		
	
	
	
}
