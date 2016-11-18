package com.techelevator;

import java.time.LocalDate;

public class Park {
	
	private Long parkId;
	private Long annualVisitorCount;
	private Long squareFootage;
	private String parkName;
	private String parkLocation;
	private String parkDescription;
	private LocalDate establishedDate;
	
	
	public Long getParkId() {
		return parkId;
	}
	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}
	public Long getAnnualVisitorCount() {
		return annualVisitorCount;
	}
	public void setAnnualVisitorCount(Long annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}
	public Long getSquareFootage() {
		return squareFootage;
	}
	public void setSquareFootage(Long squareFootage) {
		this.squareFootage = squareFootage;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getParkLocation() {
		return parkLocation;
	}
	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public LocalDate getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}
	
	

}
