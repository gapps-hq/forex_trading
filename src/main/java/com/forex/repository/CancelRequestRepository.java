package com.forex.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.Order;
import com.forex.domain.Status;

@Repository
public class CancelRequestRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public String cancelRequest(int current_id) {
		// int current_id = Integer.parseInt(order_id);
		Order current_order = jdbcTemplate.queryForObject("select * from orders where order_id = ?",
				new Object[] { current_id }, new OrderRowMapper());
		if (current_order.getStatus().equals(Status.PENDING)) {
			System.out.println("Pending check");
			String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
			System.out.println(current_order.getOrder_id());
			System.out.println("Updated");
			System.out.println(
					jdbcTemplate.update(sql, new Object[] { Status.CANCELLED.name(), current_order.getOrder_id() }));
			return "Request Cancelled";

		} else if (current_order.getStatus() == Status.CANCELLED)
			return "Your request is already cancelled";

		else if (current_order.getStatus() == Status.COMPLETED)
			return "A complete request cannot be cancelled";
		else
			return "INVALID REQUEST";
	}

	class OrderRowMapper implements RowMapper<Order> {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

			Order order = new Order();
			order.setOrder_id(rs.getInt("order_id"));
			order.setStatus(Status.valueOf(rs.getString("status")));
			return order;

		}
	}
}
