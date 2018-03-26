package com.techelevator.projects.model.daos;

import java.util.List;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Campsite;
import com.techelevator.projects.model.Reservation;

public interface CampsiteDAO {

	public List<Campsite> getCampsitesByCampgroundId(Long id);

	public List<Campsite> getAvailableCampsites(Reservation userReservation, Campground selectedCampground);

}
