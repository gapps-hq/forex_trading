package com.forex.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.Currency;
import com.forex.domain.MarketOrder;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.domain.TypeOfOrder;

@Repository
public class TraderPositionRepository 
{
	@Autowired
	private JdbcTemplate jdbctemplate;
		
	@Transactional(readOnly=true)
	public List<Order> findPendingOrder(int cust_id) 
	{
		return jdbctemplate.query("select * from orders where cust_id=? AND status='PENDING'" ,new Object[]{cust_id},new MarketOrderRowMapper());
	}

	public List<Order> findCompletedOrder(int cust_id, String frdate, String todate) throws ParseException 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromDate = dateFormat.parse(frdate);
		
		Date toDate = dateFormat.parse(todate);
		return jdbctemplate.query("select * from orders o where o.cust_id=? AND o.status='COMPLETED' AND CAST(o.transaction_time AS DATE) >= ? and CAST(o.transaction_time AS DATE) <=?" ,new Object[]{cust_id,fromDate,toDate},new TraderPositionRowMapper());
	}

		
}
class TraderPositionRowMapper implements RowMapper<Order>{
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setCurrency_base(Currency.valueOf(rs.getString("currency_base")));
		order.setCurrency_quote(Currency.valueOf(rs.getString("currency_quote")));
		order.setPrice(rs.getDouble("price"));
        order.setLot_size(rs.getInt("lot_size"));
        order.setTransaction_time(rs.getTimestamp("transaction_time"));
        order.setType_of_order(TypeOfOrder.valueOf(rs.getString("type_of_order")));
        order.setSide(Side.valueOf(rs.getString("side")));
        order.setLimit_price(rs.getDouble("limit_price"));
        order.setStatus(Status.valueOf(rs.getString("status")));
        order.setCust_id(rs.getInt("cust_id"));
       
		return order;
	}
}

