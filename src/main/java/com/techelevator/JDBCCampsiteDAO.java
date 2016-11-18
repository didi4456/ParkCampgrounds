package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampsiteDAO implements CampsiteDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCCampsiteDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);		
	}

//	@Override
//	public List<Campsite> getTop5AvailableCampsites(String name, Date fromDate, Date toDate) {
//		List<Campsite> Top5AvailableCampsites= new ArrayList<>();
//		
//		String campsite= "SELECT * FROM reservation "
//				+"JOIN site ON reservation.site_id= site.site_id "
//				+ "WHERE campground.name='?' AND from_date != BETWEEN(?) AND to_date !=(?) LIMIT 5";
//		SqlRowSet rows= jdbcTemplate.queryForRowSet(Top5AvailableCampsites,name,date,date);
//		
//		while(rows.next()) {
//			Campsite topCampsite= new Campsite();
//			topCampsite.setSiteId(rows.getInt("site_id"));
//			topCampsite.setSiteNumber(rows.getInt("site_number"));
//			topCampsite.setCampGroundId(rows.getInt("campground_id"));
//			topCampsite.setMaxOccupancy(rows.getInt("max_occupancy"));
//			topCampsite.setMaxRvLength(rows.getInt("max_rx_length"));
//			
//		}
//		
//		return Top5AvailableCampsites;
//	}



	@Override
	public List<Campsite> getTop5AvailableCampsites() {
		// TODO Auto-generated method stub
		return null;
	}}


	