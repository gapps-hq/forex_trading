package com.forex.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.*;

public class LimitOrderRepository {
    private final static Logger logger = LoggerFactory.getLogger(LimitOrderRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public LimitOrderRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }
    //price = price at which we buy
    //lot_size = amount
//    currency_pair VARCHAR(16) NOT NULL,
//	price DOUBLE NOT NULL,
//	lot_size INT NOT NULL,
//	transaction_time VARCHAR(50) NOT NULL,
//	type_of_order VARCHAR(16),
//	side VARCHAR(16) NOT NULL,
//	limit_price DOUBLE DEFAULT NULL,
//	status VARCHAR(20) NOT NULL,
    
    @Transactional
    public void saveOrder(LimitOrder order) {
      jdbcTemplate.update("insert into orders(currency_pair, price, lot_size, transaction_time, type_of_order, side, limit_price, status) values (?,?,?,?,?,?,?,?)", 
    		  	order.getCurrency().name(), order.getPrice(), order.getLot_size(),order.getTransaction_time(),order.getType_of_order(), order.getSide().name(),order.getLimit_price(),order.getStatus());
      
      
      
      //return order id
      //Currency and transaction Time
      
    }

    @Transactional(readOnly=true)
    public List<LimitOrder> findOrder(int order_id) {
        return jdbcTemplate.query("select * from orders where order_id = ?", new Object[]{order_id},
                (new LimitOrderMapper()));
    }
    
    
    class LimitOrderMapper implements RowMapper<LimitOrder> {

        @Override
        public LimitOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
          LimitOrder order = new LimitOrder();
          order.setCurrency(Currency.valueOf(rs.getString("currency_pair")));
          order.setPrice(rs.getDouble("price"));
          order.setLot_size(rs.getInt("lot_size"));
          order.setTransaction_time(rs.getString("transaction_time"));
          order.setType_of_order(rs.getString("type_of_order"));
          order.setStatus(rs.getString("status"));
          order.setLimit_price(rs.getDouble("limit_price"));
          order.setSide(Side.valueOf(rs.getString("SIDE")));
          return order;
        }

    }

}
