package com.techelevator.projects.model;

import java.time.LocalDate;
import java.util.List;

public class Park {
	//Data initialize variables
	private Long id;
	private String name;
	private String location;
	private LocalDate establishedDate;
	private Integer area; 
	private Long visitorCount;
	private String description;
	private List<Campground> campgroundsInPark;
	
	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Long getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(Long visitorCount) {
		this.visitorCount = visitorCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Campground> getCampgroundsInPark() {
		return campgroundsInPark;
	}
	public void setCampgroundsInPark(List<Campground> campgroundsInPark) {
		this.campgroundsInPark = campgroundsInPark;
	}
	
	//Logic Methods
	public String toString() {
		return this.name;
	}
	
	public String createDisplayOfParkInfo() {
	StringBuilder parkInfo = new StringBuilder();
	parkInfo.append(String.format("\n%s National Park\n", getName()));
	parkInfo.append(String.format("Location: %28s\n", getLocation()));
	parkInfo.append(String.format("Established: %32s\n", getEstablishedDate()));
	parkInfo.append(String.format("Area: %34s sq km\n", getArea()));
	parkInfo.append(String.format("Annual Visitors: %25s\n", getVisitorCount()));
	parkInfo.append(String.format("\nDescription:\n"));
	String s = getDescription();
	StringBuilder sb = new StringBuilder(s);
	int i = 0;
    while ((i = sb.indexOf(" ", i + 60)) != -1) {
        sb.replace(i, i + 1, "\n");
    }
    parkInfo.append(sb.toString());

	return parkInfo.toString();
	}
	
	public String createDisplayOfCampgroundsInPark(List<Campground> campgroundsInPark) {
		StringBuilder cgInfo = new StringBuilder();
		cgInfo.append(String.format("     Name:              Open:               Closed:          Daily Fee:\n"));
		cgInfo.append(String.format("   ----------------------------------------------------------------------\n"));
		for(Campground theCampground : campgroundsInPark) {
		
	cgInfo.append(String.format("   %-20s" , theCampground.getName()));
	cgInfo.append(String.format("%-20s" , theCampground.getOpenAsMonth()));
    cgInfo.append(String.format("%-20s" ,  theCampground.getCloseAsMonth()));
    cgInfo.append(String.format( "$" + "%-20s", theCampground.getDailyFee()));
	cgInfo.append(String.format("\n"));
	
	System.out.println();
		}
		return cgInfo.toString();
	}
	
}
