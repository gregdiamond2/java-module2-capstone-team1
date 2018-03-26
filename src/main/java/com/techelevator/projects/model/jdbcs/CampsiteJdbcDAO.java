package com.techelevator.projects.model.jdbcs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Campsite;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.daos.CampsiteDAO;

public class CampsiteJdbcDAO implements CampsiteDAO{
	//initialize jdbctemplate
	private JdbcTemplate jdbcTemplate;
	
	//Constructor
	public CampsiteJdbcDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	

@Override
public List<Campsite> getCampsitesByCampgroundId(Long id){
	
	ArrayList<Campsite> campsite = new ArrayList<>(); 
	String sqlFindCampsitesByCampgroundId = "SELECT * "+ 
									   "FROM campsite "+
									   "WHERE campground_id=? "+
									   "ORDER BY site_number ASC";
	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCampsitesByCampgroundId, id); 

	while(results.next()) { 
		Campsite theCampsite = mapRowToCampsite(results); 
		campsite.add(theCampsite); 
	}
	return campsite;
	

}

@Override
public List<Campsite> getAvailableCampsites(Reservation userReservation, Campground selectedCampground) {
	LocalDate fromDate = userReservation.getFromDate();
	LocalDate toDate = userReservation.getToDate();	
	ArrayList<Campsite> availableCampsites = new ArrayList<>(); 
		
		String sqlFindAvailableCampsites = "SELECT site.site_id, site.campground_id, site.site_number, max_occupancy, accessible, max_rv_length, utilities, daily_fee "+ 
										   "FROM site "+
										   "JOIN reservation ON reservation.site_id = site.site_id "+
										   "JOIN campground ON site.campground_id = campground.campground_id "+
										   "WHERE from_date > ? OR to_date<? AND site.campground_id=?  "+
										   "GROUP BY site.site_id, site.campground_id, site.site_number, max_occupancy, accessible, max_rv_length, utilities, daily_fee "+
										   "LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindAvailableCampsites, toDate, fromDate, selectedCampground.getId()); 

		while(results.next()) { 
			Campsite theCampsite = mapRowToCampsite(results); 
			theCampsite.setCost(userReservation);
			availableCampsites.add(theCampsite); 
		}
		return availableCampsites;
	
	
}



private Campsite mapRowToCampsite(SqlRowSet results) {
	Campsite theCampsite;
	theCampsite = new Campsite();
	theCampsite.setId(results.getLong("site_id"));
	theCampsite.setIdFKCG(results.getLong("campground_id"));
	theCampsite.setSiteNumber(results.getInt("site_number"));
	theCampsite.setMaxOccupancy(results.getInt("max_occupancy"));
	theCampsite.setAccessible(((results.getBoolean("accessible")) ? "Yes" : "No").toString());
	theCampsite.setMaxRvLength(results.getInt("max_rv_length"));
	theCampsite.setUtilities(((results.getBoolean("utilities")) ? "Yes" : "No").toString());
	theCampsite.setDailyFee(new BigDecimal (results.getDouble("daily_fee")));
	
	return theCampsite;
}
}
