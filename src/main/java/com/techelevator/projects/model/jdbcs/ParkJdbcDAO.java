package com.techelevator.projects.model.jdbcs;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.daos.ParkDAO;

public class ParkJdbcDAO implements ParkDAO{
	
	//initialize jdbctemplate
	private JdbcTemplate jdbcTemplate;
	
	//Constructor
	public ParkJdbcDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks(){
		ArrayList<Park> allParks = new ArrayList<>(); 
		String sqlGetAllParks = "SELECT * "+ 
										   "FROM park ";
										  
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks); 
	
		while(results.next()) { 
			Park thePark = mapRowToPark(results); 
			allParks.add(thePark); 
		}
		return allParks;
	}
	
	
	@Override
	public Park getParkById(Long id) {
		Park thePark = null;
		String sqlFindParkById = "SELECT * "+
							   "FROM park "+
							   "WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindParkById, id);
		if(results.next()) {
			thePark = mapRowToPark(results);
		}
		return thePark;
		
	}
	
	
	private Park mapRowToPark(SqlRowSet results) {
		Park thePark;
		thePark = new Park();
		thePark.setId(results.getLong("park_id"));
		thePark.setName(results.getString("name"));
		thePark.setLocation(results.getString("location"));
		thePark.setEstablishedDate(results.getDate("establish_date").toLocalDate());
		thePark.setArea(results.getInt("area"));
		thePark.setVisitorCount(results.getLong("visitors"));
		thePark.setDescription(results.getString("description"));
		
		
		return thePark;
	}
	
	


}
