package com.forex.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.Currency;
import com.forex.domain.MarketOrder;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.domain.TypeOfOrder;

@Repository
public class MarketOrderRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;

    
	@Transactional
	public int placeMarketOrder(final Order marketorder) 
	{
		KeyHolder holder = new GeneratedKeyHolder();
	    final String sql = "insert into orders(currency_base, currency_quote, lot_size, transaction_time, type_of_order, side, limit_price, status, cust_id) values (?,?,?,?,?,?,?,?,?)";
	    jdbctemplate.update(new PreparedStatementCreator() {
	        
	        @Override
	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	            // TODO Auto-generated method stub
	            PreparedStatement saveOrderStatement = con.prepareStatement(sql);
	            saveOrderStatement.setString(1,  marketorder.getCurrency_base().name());
	            saveOrderStatement.setString(2,  marketorder.getCurrency_quote().name());
	            saveOrderStatement.setInt(3, marketorder.getLot_size());
	            saveOrderStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
	            saveOrderStatement.setString(5, TypeOfOrder.MARKET.name());
	            saveOrderStatement.setString(6, marketorder.getSide().name());
	            saveOrderStatement.setDouble(7, marketorder.getLimit_price());
	            saveOrderStatement.setString(8, Status.PENDING.name());
	            saveOrderStatement.setInt(9, 1);


	            return saveOrderStatement;
	        }
	    },
	    holder);
	   
	   return holder.getKey().intValue();
	  }
	
	@Transactional(readOnly=true)
	public Order findMarketOrder(int order_id) 
	{
		return jdbctemplate.queryForObject("select * from orders where order_id=?",new Object[]{order_id},new MarketOrderRowMapper());
	}

	@Transactional(readOnly=true)
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("select * from orders",new MarketOrderRowMapper());
	}

}

class MarketOrderRowMapper implements RowMapper<Order>{
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
