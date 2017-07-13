package com.forex.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.forex.domain.Status.COMPLETED;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.forex.domain.Status.CANCELLED;
import com.forex.domain.Side;
import com.forex.domain.TYPE_OF_CUSTOMERS;
import com.forex.domain.Order;
import com.forex.domain.Audit;
import com.forex.domain.Costumers;

import com.forex.domain.LimitOrder;
import com.forex.domain.Status;
import com.forex.domain.Currency;

@Repository
public class AuditRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional(readOnly=true)
    public int AuditorRepository(Costumers customer)
    {
    	System.out.println(customer.getType_of_customer());
        if(customer.getType_of_customer() == TYPE_OF_CUSTOMERS.AUDITOR)
        {
        	System.out.println(customer.getType_of_customer());
        	List<Order> audit_order_table = jdbcTemplate.query("select * from orders where status = ? OR status = ?",
    				new Object[] { Status.COMPLETED.name(), Status.CANCELLED.name() }, new OrderRowMapper());
         
        	System.out.print("LIST FOUND");
        	System.out.println(audit_order_table.get(0).getOrder_id());
        	System.out.println(audit_order_table.get(0).getCurrency_base().name());
        	System.out.println(audit_order_table.get(0).getCurrency_quote().name());
        	System.out.println(audit_order_table.get(0).getLot_size());
        	System.out.println(audit_order_table.get(0).getPrice());
        	System.out.println(audit_order_table.get(0).getTransaction_time());
        	System.out.println(audit_order_table.get(0).getStatus().name());
        	String sql = "INSERT INTO AUDIT_TABLE(order_id, currency_base,currency_quote, lot_size, price, time_created, time_updated,status) VALUES(?,?,?,?,?,?,?,?)";
        	if(audit_order_table.isEmpty()){
        		System.out.println("LIST NOT FOUND");
        		return -1;
        	}
        	for(int i=0; i<audit_order_table.size();i++)
        	{
        		jdbcTemplate.update(sql, new Object[] { (audit_order_table.get(i)).getOrder_id(),
        				(audit_order_table.get(i)).getCurrency_base().name(),
        				(audit_order_table.get(i)).getCurrency_quote().name(),
        				(audit_order_table.get(i)).getLot_size(),
        				(audit_order_table.get(i)).getPrice(),
        				(audit_order_table.get(i)).getTransaction_time(),
        				new Timestamp(System.currentTimeMillis()),
        				(audit_order_table.get(i)).getStatus().name()
        				});
			
        		System.out.println("Adding list to audit table");
        	}
        	System.out.println("Audit Updated");
        }
        return 0;
    }
}

class OrderRowMapper implements RowMapper<Order> {
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

		Order order = new Order();
		order.setCurrency_base(Currency.valueOf(rs.getString("Currency_base")));
		order.setCurrency_quote(Currency.valueOf(rs.getString("Currency_quote")));
		order.setLot_size(rs.getInt("Lot_size"));
		order.setPrice(rs.getDouble("Price"));
		order.setOrder_id(rs.getInt("Order_id"));
		order.setTransaction_time(rs.getTimestamp("transaction_time"));
		order.setStatus(Status.valueOf(rs.getString("status")));
		//order.setTime_updated(rs.getTimestamp("time_updated"));
		return order;

	}
}
