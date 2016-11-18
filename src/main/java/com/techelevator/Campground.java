package com.techelevator;

public class Campground {

	private int campGroundId;
	private String campGroundName;
	private int openMonth;
	private int closedMonth;
	private double dailyFee;
	
	
	public int getCampGroundId() {
		return campGroundId;
	}
	public void setCampGroundId(int campGroundId) {
		this.campGroundId = campGroundId;
	}
	public String getCampGroundName() {
		return campGroundName;
	}
	public void setCampGroundName(String campGroundName) {
		this.campGroundName = campGroundName;
	}
	public int getOpenMonth() {
		return openMonth;
	}
	public void setOpenMonth(int openMonth) {
		this.openMonth = openMonth;
	}
	public int getClosedMonth() {
		return closedMonth;
	}
	public void setClosedMonth(int closedMonth) {
		this.closedMonth = closedMonth;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}
	
}
