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
import com.techelevator.projects.model.Campsite;

public class CampsiteJdbcDAOTest {

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
	public void testGetCampsitesByCampgroundId() {
		fail("Not yet implemented");
	}
	
	
//	@Override
//	public List<Campsite> getCampsitesByCampgroundId(Long id){
//		
//		ArrayList<Campsite> campsite = new ArrayList<>(); 
//		String sqlFindCampsitesByCampgroundId = "SELECT * "+ 
//										   "FROM campsite "+
//										   "WHERE campground_id=? "+
//										   "ORDER BY site_number ASC";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCampsitesByCampgroundId, id); 
//
//		while(results.next()) { 
//			Campsite theCampsite = mapRowToCampsite(results); 
//			campsite.add(theCampsite); 
//		}
//		return campsite;
//		
//
//	}

	@Test
	public void testGetAvailableCampsites() {
		fail("Not yet implemented");
	}
	

//
//	@Override
//	public List<Campsite> getAvailableCampsites(LocalDate toDate, LocalDate fromDate, Campground selectedCampground) {
//			ArrayList<Campsite> availableCampsites = new ArrayList<>(); 
//			
//			String sqlFindAvailableCampsites = "SELECT site.site_id, site.campground_id, site.site_number, max_occupancy, accessible, max_rv_length, utilities, daily_fee "+ 
//											   "FROM site "+
//											   "JOIN reservation ON reservation.site_id = site.site_id "+
//											   "JOIN campground ON site.campground_id = campground.campground_id "+
//											   "WHERE from_date > ? OR to_date<? AND site.campground_id=?  "+
//											   "GROUP BY site.site_id, site.campground_id, site.site_number, max_occupancy, accessible, max_rv_length, utilities, daily_fee "+
//											   "LIMIT 5";
//			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindAvailableCampsites, toDate, fromDate, selectedCampground.getId()); 
//
//			while(results.next()) { 
//				Campsite theCampsite = mapRowToCampsite(results); 
//				availableCampsites.add(theCampsite); 
//			}
//			return availableCampsites;
//		
//		
//	}

}
