package com.techelevator;

public class Campsite {

	private int siteId;
	private int siteNumber;
	private int maxOccupancy;
	private int maxRvLength;
	private int campGroundId;
	
	
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public int getMaxRvLength() {
		return maxRvLength;
	}
	public void setMaxRvLength(int maxRvLength) {
		this.maxRvLength = maxRvLength;
	}
	public int getCampGroundId() {
		return campGroundId;
	}
	public void setCampGroundId(int campGroundId) {
		this.campGroundId = campGroundId;
	}
	
}
