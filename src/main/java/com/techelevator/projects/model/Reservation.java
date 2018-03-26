package com.techelevator.projects.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
	//Data initialize variables
			private Long id;
			private Long idFKSite;
			private String nameRsrv;
			private LocalDate fromDate;
			private LocalDate toDate;
			private LocalDate createDate;
			private Park selectedPark;
			private Campground selectedCampground;
			private Campsite selectedCampsite;
			private Long lengthOfStay;
				//Getters and Setters
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public Long getIdFKSite() {
				return idFKSite;
			}
			public void setIdFKSite(Long idFKSite) {
				this.idFKSite = idFKSite;
			}
			public String getNameRsrv() {
				return nameRsrv;
			}
			public void setNameRsrv(String nameRsrv) {
				this.nameRsrv = nameRsrv;
			}
			public LocalDate getFromDate() {
				return fromDate;
			}
			public void setFromDate(LocalDate fromDate) {
				this.fromDate = fromDate;
			}
			public LocalDate getToDate() {
				return toDate;
			}
			public void setToDate(LocalDate toDate) {
				this.toDate = toDate;
			}
			public LocalDate getCreateDate() {
				return createDate;
			}
			public void setCreateDate(LocalDate createDate) {
				this.createDate = createDate;
			}
			public Park getSelectedPark() {
				return selectedPark;
			}
			public void setSelectedPark(Park selectedPark) {
				this.selectedPark = selectedPark;
			}
			public Campground getSelectedCampground() {
				return selectedCampground;
			}
			public void setSelectedCampground(Campground selectedCampground) {
				this.selectedCampground = selectedCampground;
			}
			public Campsite getSelectedCampsite() {
				return selectedCampsite;
			}
			public void setSelectedCampsite(Campsite selectedCampsite) {
				this.selectedCampsite = selectedCampsite;
			}
			public Long getLengthOfStay() {
				return lengthOfStay;
			}
			public void setLengthOfStay() {
				Long dateDiff = ChronoUnit.DAYS.between(fromDate, toDate);
				this.lengthOfStay = dateDiff;
						
			}
			
			
}
