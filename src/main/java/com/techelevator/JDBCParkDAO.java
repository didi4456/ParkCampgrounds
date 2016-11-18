package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);		
	}

	@Override
	public List<Park> listAllParks() {
		List<Park> allParks = new ArrayList<>();
		String parks = "SELECT * FROM park";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(parks);
		
		
		while(rows.next()) {
			Park park = new Park();
			park.setParkId(rows.getLong("park_Id"));
			park.setParkName(rows.getString("name"));
			allParks.add(park);
		}
		return allParks;
	}

	@Override
	public List<Park> selectParkInfoByName(String name) {
		List<Park> parkByName = new ArrayList<>();
		String sqlGetParkInfo = "SELECT * FROM park WHERE name = ?;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlGetParkInfo, name);
		
		while(rows.next()) {
			Park park = new Park();
			park.setParkId(rows.getLong("park_id"));
			park.setParkName(rows.getString("name"));
			park.setParkLocation(rows.getString("location"));
			park.setEstablishedDate(rows.getDate("establish_date").toLocalDate());
			park.setSquareFootage(rows.getLong("area"));
			park.setAnnualVisitorCount(rows.getLong("visitors"));
			park.setParkDescription(rows.getString("description"));
			parkByName.add(park);
		}
		return parkByName;
	}

	@Override
	public List<Park> selectParkByName(String name) {
		List<Park> parkName = new ArrayList<>();
		String sqlGetParkInfo = "SELECT name FROM park WHERE name = ?;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlGetParkInfo, name);

		while (rows.next()) {
			Park park = new Park();
			// park.setParkId(rows.getLong("park_id"));
			park.setParkName(rows.getString("name"));
			park.setParkLocation(rows.getString("location"));
			park.setEstablishedDate(rows.getDate("establish_date").toLocalDate());
			park.setSquareFootage(rows.getLong("area"));
			park.setAnnualVisitorCount(rows.getLong("visitors"));
			park.setParkDescription(rows.getString("description"));
			parkName.add(park);
		}
		return parkName;
	}
}