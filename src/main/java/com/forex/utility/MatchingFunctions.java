package com.forex.utility;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.Historical;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.utility.mappers.HistoricalRowMapper;

public class MatchingFunctions {
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public boolean checkBaseCurrency(Order order1, Order order2){
		return order1.getCurrency_base().equals(order2.getCurrency_base());
	}
	 
	public boolean checkQuoteCurrency(Order order1, Order order2){
		return order1.getCurrency_quote().equals(order2.getCurrency_quote());

	}
	
	public boolean checkLotSize(Order order1, Order order2){
		return order1.getLot_size() == order2.getLot_size();

	}
	public boolean checkSide(Order order1, Order order2){
		return (order1.getSide().equals(Side.BUY) && order2.getSide().equals(Side.SELL)) ||
				(order1.getSide().equals(Side.SELL) && order2.getSide().equals(Side.BUY));

	}
	public double getCurrentMarketPrice(Order order, JdbcTemplate jdbcTemplate){
		final String sql = "SELECT * FROM historical_data WHERE currency_base = ? AND currency_quote = ? ORDER BY time_completed DESC;";
		List<Historical> latestOrders = jdbcTemplate.query(sql, new Object[]{order.getCurrency_base(), order.getCurrency_quote()}, new HistoricalRowMapper());
		if(latestOrders.isEmpty()){
			return -1;
		}
		return latestOrders.get(0).getPrice();
	}
	
	@Transactional
	public void executeOrder(Order order1, Order order2, JdbcTemplate jdbcTemplate){
		updateStatus(order1, jdbcTemplate);
		updateStatus(order2, jdbcTemplate);
		insertOrderIntoHistorical(order1, jdbcTemplate);
		insertOrderIntoHistorical(order2, jdbcTemplate);
	}
	public int updateStatus(Order order, JdbcTemplate jdbcTemplate){
		final String sql = "UPDATE orders SET status = ?, time_updated = ? WHERE order_id = ?";
		return jdbcTemplate.update(sql,new Object[]{Status.COMPLETED.name(), order.getOrder_id(), timestamp});		
	}
	
	public boolean insertOrderIntoHistorical(Order order, JdbcTemplate jdbcTemplate){
		Historical historical = new Historical();
		historical.setCurr_base(order.getCurrency_base());
		historical.setCurr_quote(order.getCurrency_quote());
		historical.setLot_size(order.getLot_size());
		historical.setOrder_id(order.getOrder_id());
		historical.setPrice(order.getPrice());
		historical.setTransaction_time(timestamp);
		final String sql = "INSERT INTO historical_data(order_id, currency_base, currency_quote, price, lot_size, time_completed) VALUES(?,?,?,?,?,?)";
		
		int noOfRows = jdbcTemplate.update(sql, new Object[]{
				historical.getOrder_id(),
				historical.getCurr_base(),
				historical.getCurr_quote(),
				historical.getPrice(),
				historical.getLot_size(),
				historical.getTransaction_time()
		});
		if(noOfRows >1){
			return true;
		}
		return false;
	}
}
