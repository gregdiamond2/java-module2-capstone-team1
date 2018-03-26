package com.techelevator.projects.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {

	//Data initialize variables
	private Long id;
	private Long parkIdFKCG;
	private String name;
	private String open;
	private String close;
	private BigDecimal dailyFee;
	
	//Getters and Setters

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParkIdFKCG() {
		return parkIdFKCG;
	}
	public void setParkIdFKCG(Long parkIdFKCG) {
		this.parkIdFKCG = parkIdFKCG;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String string) {
		this.open = string;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String string) {
		this.close = string;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	} 
	
	//Logic Methods
		public String toString() {
			return this.name;
		}
		
		public String getOpenAsMonth() {
			return convertStringToMonth(open);
		}
		public String getCloseAsMonth() {
			return convertStringToMonth(close);
		}
		public LocalDate getOpenAsLocalDate(LocalDate userFromDate) {
			return convertStringToLocalDate(open, userFromDate);
		}
		public LocalDate getCloseAsLocalDate(LocalDate userToDate) {
			return convertStringToLocalDate(close, userToDate);
		}
		
		
		
		private String convertStringToMonth(String input) {
			if(input.equals("01")) {
				return "January";
			}else if(input.equals("02")) {
				return "February";
			}else if(input.equals("03")) {
				return "March";
			}else if(input.equals("04")) {
				return "April";
			}else if(input.equals("05")) {
				return "May";
			}else if(input.equals("06")) {
				return "June";
			}else if(input.equals("07")) {
				return "July";
			}else if(input.equals("08")) {
				return "August";
			}else if(input.equals("09")) {
				return "September";
			}else if(input.equals("10")) {
				return "October";
			}else if(input.equals("11")) {
				return "November";
			}else if(input.equals("12")) {
				return "December";
			}else return "--no month listed--";

		}
		
		private LocalDate convertStringToLocalDate(String input, LocalDate userDate) {
			String newDate = userDate.getYear() + "-" + input + "-01"; //e.g display 2018-01-01
			return LocalDate.parse(newDate);
		}

	

}
