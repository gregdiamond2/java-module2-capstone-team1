package com.techelevator.projects.model.daos;

import java.util.List;

import com.techelevator.projects.model.Campground;

public interface CampgroundDAO {
	

	public List<Campground> getCampgroundsByParkId(Long id);
}
