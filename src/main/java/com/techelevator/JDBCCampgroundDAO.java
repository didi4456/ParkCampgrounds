package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampgroundDAO implements CampgroundDAO {
private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);		
	}

	@Override
	public List<Campground> getAllCampgrounds() {
		List <Campground> campgrounds= new ArrayList<>();
		String allCampgrounds="SELECT * FROM campground ";
		SqlRowSet rows= jdbcTemplate.queryForRowSet(allCampgrounds);
		
		while(rows.next()) {
			Campground campground= new Campground();
			campground.setCampGroundName(rows.getString("name"));
			campground.setCampGroundId(rows.getInt("campgroundId"));
			campground.setOpenMonth(rows.getInt("open_from_mm"));
			campground.setClosedMonth(rows.getInt("open_to_mm"));
			campground.setDailyFee(rows.getInt("daily_fee"));
		}
		return campgrounds;
	}

	@Override
	public List<Campground> selectCampgroundByCampgroundName() {
		List<Campground> selectCampgrounds= new ArrayList<>();
		String campground= "SELECT * FROM campground WHERE name = ?;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(campground);
		
		while (rows.next()) {
			Campground campgrounds= new Campground();
			campgrounds.setCampGroundName(rows.getString("name"));
			campgrounds.setCampGroundId(rows.getInt("campgroundId"));
			campgrounds.setOpenMonth(rows.getInt("open_from_mm"));
			campgrounds.setClosedMonth(rows.getInt("open_to_mm"));
			campgrounds.setDailyFee(rows.getInt("daily_fee"));
		}
		return selectCampgrounds;
	}
	
	public List<Campground> getCampgroundByParkName(String parkname) {
		
		List<Campground> displayingCampground = new ArrayList<>();
		String allCampgrounds = "SELECT *"
								+ "FROM campground "
								+ "JOIN park ON campground.park_id = park.park_id "
								+ "WHERE park.name = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(allCampgrounds,parkname);
		
		while(rows.next()) {
			Campground campground= new Campground();
			campground.setCampGroundName(rows.getString("name"));
			campground.setCampGroundId(rows.getInt("campground_id"));
			campground.setOpenMonth(rows.getInt("open_from_mm"));
			campground.setClosedMonth(rows.getInt("open_to_mm"));
			campground.setDailyFee(rows.getInt("daily_fee"));
			displayingCampground.add(campground);
		}
		return displayingCampground;
	}

}
