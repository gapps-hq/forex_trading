package com.forex.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderMatchingRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int matchOrder(){
		int cp = 1;
		  
		return 1;
	}
	
	
}
