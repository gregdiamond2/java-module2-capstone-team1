package com.techelevator.projects.model.daos;

import java.util.List;

import com.techelevator.projects.model.Reservation;

public interface ReservationDAO {
	public List<Reservation> getReservationByCampsiteId(Long idSite);
	public Long createReservation(Reservation userReservation);
	public Boolean checkIfReservationValidByCampground(Reservation userReservation);
}
