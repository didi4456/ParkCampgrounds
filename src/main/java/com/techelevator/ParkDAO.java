package com.techelevator;

import java.util.List;

public interface ParkDAO {

	public List<Park> listAllParks();
	public List<Park> selectParkInfoByName(String name);
	public List<Park> selectParkByName(String name);
	
}
