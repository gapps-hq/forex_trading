package com.forex.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.forex.Application;
import com.forex.domain.Currency;
import com.forex.domain.LimitOrder;
import com.forex.domain.Status;
import com.forex.repository.AuditRepository;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class})
public class AuditTest {

	@Autowired
	AuditRepository aud_repo;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	List<Status> status_list; 
	
	@Before
	public void getAllStatusForAuditTable()
	{
		status_list = jdbcTemplate.query("SELECT status from AUDIT_TABLE where status = ? OR status = ?",
				new Object[] { Status.COMPLETED.name(), Status.CANCELLED.name() }, new OrderRowMapper() );
		
		System.out.println(status_list);
	}
	
	@Test
	public void AuditRepoTest()
	{
		String current_status1 = "COMPLETED";
		String current_status2 = "CANCELLED";
		for(int i=0;i<status_list.size();i++)
			assertThat("status is COMPLETED or CANCELLED", status_list.get(i).name(), Matchers.anyOf(equalTo(current_status1), equalTo(current_status2)));
	}
	
	
	
}

class OrderRowMapper implements RowMapper<Status> {
	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {

		/*LimitOrder order = new LimitOrder();
		order.setCurrency_base(Currency.valueOf(rs.getString("Currency_base")));
		order.setCurrency_quote(Currency.valueOf(rs.getString("Currency_quote")));
		order.setLot_size(rs.getInt("Lot_size"));
		order.setPrice(rs.getInt("Price"));
		order.setOrder_id(rs.getInt("Order_id"));
		*/
		Status current_status = Status.valueOf(rs.getString("status")); 
		return current_status;

	}
}
