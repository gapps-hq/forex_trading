package com.forex.utility.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.forex.domain.Currency;
import com.forex.domain.Historical;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.domain.TypeOfOrder;

public class HistoricalRowMapper implements RowMapper<Historical>{
	@Override
	public Historical mapRow(ResultSet rs, int rowNum) throws SQLException {
		Historical historical = new Historical();
		historical.setRef_id(rs.getInt("ref_id"));
		historical.setOrder_id(rs.getInt("order_id"));
		historical.setCurr_base(Currency.valueOf(rs.getString("currency_base")));
		historical.setCurr_quote(Currency.valueOf(rs.getString("currency_quote")));
		historical.setPrice(rs.getDouble("price"));
        historical.setLot_size(rs.getInt("lot_size"));
        historical.setTransaction_completed(rs.getTimestamp("time_completed"));       
		return historical;
	}
	
}
