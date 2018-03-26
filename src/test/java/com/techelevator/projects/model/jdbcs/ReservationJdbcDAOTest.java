package com.techelevator.projects.model.jdbcs;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Reservation;

public class ReservationJdbcDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateReservation() {
		fail("Not yet implemented");
	}
	
//	@Override
//	public Long createReservation(Long selectedSiteId, String name, LocalDate fromDate, LocalDate toDate) {
//			String sqlInsertDepartment = "INSERT INTO reservation(site_id, name, from_date, to_date, create_date) " + 
//					   "VALUES(?, ?, ?, ?, current_date) RETURNING reservation_id" ;
//			
//			
//			 Long reservationId = jdbcTemplate.queryForObject(sqlInsertDepartment, Long.class, selectedSiteId, name, fromDate, toDate);
//											  
//				return 	reservationId;						  
//		}

	
	@Test
	public void testGetReservationByCampsiteId() {
		fail("Not yet implemented");
	}
//	@Override
//	public List<Reservation> getReservationByCampsiteId(Long idSite) {
//		ArrayList<Reservation> reservations = new ArrayList<>(); 
//		String sqlFindReservationByCampsiteId = "SELECT * "+ 
//										   "FROM reservation "+
//										   "WHERE campsite_id=? "+
//										   "ORDER BY site_number ASC";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindReservationByCampsiteId, idSite); 
//
//		while(results.next()) { 
//			Reservation theReservation = mapRowToReservation(results); 
//			reservations.add(theReservation); 
//		}
//		return reservations;
//	}
	
//	@Override
//	public Boolean checkIfReservationValidByCampground(LocalDate fromDate, LocalDate toDate,Campground selectedCampground) {
//		return true;
//	}

}
