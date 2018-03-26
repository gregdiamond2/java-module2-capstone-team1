package com.techelevator.projects.model.daos;

import java.util.List;

import com.techelevator.projects.model.Park;

public interface ParkDAO {
	public List<Park> getAllParks();
	public Park getParkById(Long id);
	
	
}
