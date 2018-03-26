package com.techelevator.projects.model.jdbcs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.daos.ReservationDAO;

public class ReservationJdbcDAO implements ReservationDAO{
	//initialize jdbctemplate
		private JdbcTemplate jdbcTemplate;
		
		//Constructor
		public ReservationJdbcDAO(DataSource dataSource) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

	@Override
	public Long createReservation(Reservation userReservation) {
			String sqlInsertDepartment = "INSERT INTO reservation(site_id, name, from_date, to_date, create_date) " + 
					   "VALUES(?, ?, ?, ?, current_date) RETURNING reservation_id" ;
			
			
			 Long reservationId = jdbcTemplate.queryForObject(sqlInsertDepartment, Long.class, userReservation.getIdFKSite(), userReservation.getNameRsrv(), userReservation.getFromDate(), userReservation.getToDate());
											  
				return 	reservationId;						  
			
		}
	
	@Override
	public List<Reservation> getReservationByCampsiteId(Long idSite) {
		ArrayList<Reservation> reservations = new ArrayList<>(); 
		String sqlFindReservationByCampsiteId = "SELECT * "+ 
										   "FROM reservation "+
										   "WHERE campsite_id=? "+
										   "ORDER BY site_number ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindReservationByCampsiteId, idSite); 

		while(results.next()) { 
			Reservation theReservation = mapRowToReservation(results); 
			reservations.add(theReservation); 
		}
		return reservations;
	}
	
	@Override
	public Boolean checkIfReservationValidByCampground(Reservation userReservation) {
		LocalDate fromDate = userReservation.getFromDate();
		LocalDate toDate = userReservation.getToDate();
		Campground selectedCampground = userReservation.getSelectedCampground();
		Boolean validFromDate = (fromDate.compareTo(selectedCampground.getOpenAsLocalDate(fromDate)) >= 0) ? true : false;
		Boolean validToDate = (toDate.compareTo(selectedCampground.getCloseAsLocalDate(toDate)) < 0) ? true : false;	
		return (validFromDate && validToDate) ? true : false;
	}

	

	
	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation theReservation;
		theReservation = new Reservation();
		theReservation.setId(results.getLong("reservation_id"));
		theReservation.setId(results.getLong("site_id"));
		theReservation.setIdFKSite(results.getLong("campsite_id"));
		theReservation.setNameRsrv(results.getString("name"));
		theReservation.setFromDate(results.getDate("from_date").toLocalDate());
		theReservation.setToDate(results.getDate("to_date").toLocalDate());
		theReservation.setCreateDate(results.getDate("create_date").toLocalDate());
		
		
		return theReservation;
	}
}
