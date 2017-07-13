package com.forex.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.Currency;
import com.forex.domain.Historical;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.domain.TypeOfOrder;

public class MatchingFunctions {
	public boolean checkBaseCurrency(Order order1, Order order2){
		return order1.getCurrency_base() == (order2.getCurrency_base());
	}
	 
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
    public double getCurrentMarketPrice(Order order, JdbcTemplate jdbcTemplate){
        final String sql = "SELECT * FROM historical_data WHERE currency_base = ? AND currency_quote = ? ORDER BY time_completed DESC;";
        List<Historical> latestOrders = jdbcTemplate.query(sql, new Object[]{order.getCurrency_base().name(), order.getCurrency_quote().name()}, new HistoricalRowMapper());
        System.out.println(order.getCurrency_base().name()+" "+order.getCurrency_quote().name()+"\n");
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
        return jdbcTemplate.update(sql,new Object[]{Status.COMPLETED.name(), timestamp, order.getOrder_id()});        
    }
    
    public boolean insertOrderIntoHistorical(Order order, JdbcTemplate jdbcTemplate){
        Historical historical = new Historical();
        historical.setCurr_base(order.getCurrency_base());
        historical.setCurr_quote(order.getCurrency_quote());
        historical.setLot_size(order.getLot_size());
        historical.setOrder_id(order.getOrder_id());
        historical.setPrice(order.getPrice());
        historical.setTransaction_completed(timestamp);
        final String sql = "INSERT INTO historical_data(order_id, currency_base, currency_quote, price, lot_size, time_completed) VALUES(?,?,?,?,?,?)";
        
        int noOfRows = jdbcTemplate.update(sql, new Object[]{
                historical.getOrder_id(),
                historical.getCurr_base().name(),
                historical.getCurr_quote().name(),
                historical.getPrice(),
                historical.getLot_size(),
                historical.getTransaction_completed()
        });
        if(noOfRows >1){
            return true;
        }
        return false;
    }
	
	
	public double[] orderWithOrder(Order order1, Order order2, double cp){
		
		double r[] = new double[2];
		
		if(cp == -1){
			if(order1.getType_of_order() == order2.getType_of_order()){
				if(order1.getType_of_order() == TypeOfOrder.MARKET){
					r[0] = -1;
					return r;
				}else{
					if(order1.getLimit_price() == order2.getLimit_price()){
						r[0] = order1.getLimit_price();
						r[1] = order1.getLimit_price();
						return r;
					}
				}
			}else{
				if(order1.getType_of_order() == TypeOfOrder.LIMIT){
					r[0] = order1.getLimit_price();
					r[1] = order1.getLimit_price();
				}else{
					r[0] = order2.getLimit_price();
					r[1] = order2.getLimit_price();
				}
				return r;
			}			
		}		
		if(order1.getType_of_order() == order2.getType_of_order()){
			if(order1.getType_of_order() == TypeOfOrder.MARKET){
				r[0] = cp;
				r[1] = cp;
				return r;
			}else{
				if(order1.getLimit_price() == order2.getLimit_price()){
					r[0] = order1.getLimit_price();
					r[1] = order1.getLimit_price();
					return r;
				}
			}
		}else{
			if(order1.getType_of_order() == TypeOfOrder.LIMIT){
				if(order1.getSide() == Side.BUY){
					if(order1.getLimit_price() >= cp){
						r[0] = order1.getLimit_price();
						r[1] = cp;
						return r;
					}
				}else{
					if(order1.getLimit_price() <= cp){
						r[0] = order1.getLimit_price();
						r[1] = cp;
						return r;
					}
				}
			}else{
				if(order2.getSide() == Side.BUY){
					if(order2.getLimit_price() >= cp){
						r[1] = order2.getLimit_price();
						r[0] = cp;
						return r;
					}
				}else{
					if(order2.getLimit_price() <= cp){
						r[1] = order2.getLimit_price();
						r[0] = cp;
						return r;
					}
				}
			}
		}
		return r;
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
	
}
