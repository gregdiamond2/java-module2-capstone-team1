package com.techelevator.projects.model;

import java.math.BigDecimal;

public class Campsite {
	//Data initialize variables
		private Long id;
		private Long idFKCG;
		private Integer siteNumber;
		private Integer maxOccupancy;
		private String accessible;
		private Integer maxRvLength;
		private String utilities;
		private BigDecimal fee;
		private BigDecimal cost = new BigDecimal("0");
		
		//Getters and Setters
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getIdFKCG() {
			return idFKCG;
		}
		public void setIdFKCG(Long idFKCG) {
			this.idFKCG = idFKCG;
		}
		public Integer getSiteNumber() {
			return siteNumber;
		}
		public void setSiteNumber(Integer siteNumber) {
			this.siteNumber = siteNumber;
		}
		public Integer getMaxOccupancy() {
			return maxOccupancy;
		}
		public void setMaxOccupancy(Integer maxOccupancy) {
			this.maxOccupancy = maxOccupancy;
		}
		public String getAccessible() {
			return accessible;
		}
		public void setAccessible(String string) {
			this.accessible = string;
		}
		public Integer getMaxRvLength() {
			return maxRvLength;
		}
		public void setMaxRvLength(Integer maxRvLength) {
			this.maxRvLength = maxRvLength;
		}
		public String getUtilities() {
			return utilities;
		}
		public void setUtilities(String string) {
			this.utilities = string;
		}
		public BigDecimal getDailyFee() {
			return fee;
		}
		public void setDailyFee(BigDecimal fee) {
			this.fee = fee;
		}
		public BigDecimal getCost() {
			return cost;
		}
		public void setCost(Reservation userReservation) {
			BigDecimal lengthOfStay = new BigDecimal(userReservation.getLengthOfStay());
			
			this.cost = this.fee.multiply(lengthOfStay);
			
		} 
		
		//Logic Methods
			public String toString() {
				StringBuilder str = new StringBuilder();
				str.append(String.format("%s", this.siteNumber.toString()));
				str.append(String.format("%15s", this.maxOccupancy.toString())); 
				str.append(String.format("%15s", this.accessible));
				str.append(String.format("%15s", this.maxRvLength.toString()));
				str.append(String.format("%15s", this.utilities));
				str.append(String.format("%15s0", "$" + this.cost.toString()));
				return str.toString();
			}
			
			
			
}
