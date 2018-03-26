package com.techelevator.projects.model.jdbcs;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.daos.CampgroundDAO;

public class CampgroundJdbcDAO implements CampgroundDAO{
	//initialize jdbctemplate
		private JdbcTemplate jdbcTemplate;
		
		//Constructor
		public CampgroundJdbcDAO(DataSource dataSource) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

		
	
	@Override
	public List<Campground> getCampgroundsByParkId(Long id) {
		
		ArrayList<Campground> campground = new ArrayList<>(); 
		String sqlFindCampgroundByParkId = "SELECT * "+ 
										   "FROM campground "+
										   "WHERE park_id=? "+
										   "ORDER BY name ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCampgroundByParkId, id); 
	
		while(results.next()) { 
			Campground theCampground = mapRowToCampground(results); 
			campground.add(theCampground); 
		}
		return campground;
		
	}
	
	
	
	
	
	Campground mapRowToCampground(SqlRowSet results) {
		Campground theCampground;
		theCampground = new Campground();
		theCampground.setId(results.getLong("campground_id"));
		theCampground.setParkIdFKCG(results.getLong("park_id"));
		theCampground.setName(results.getString("name"));
		theCampground.setOpen(results.getString("open_from_mm"));
		theCampground.setClose(results.getString("open_to_mm"));
		theCampground.setDailyFee(results.getBigDecimal("daily_fee"));		
		
		return theCampground;
	}


	
	

	
	
}