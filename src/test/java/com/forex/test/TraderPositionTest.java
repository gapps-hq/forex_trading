package com.forex.test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forex.Application;
import com.forex.domain.Currency;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.repository.MarketOrderRepository;
import com.forex.repository.TraderPositionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class})

public class TraderPositionTest 
{
	@Autowired
	TraderPositionRepository traderpositionrepo;
	
	@Autowired
	MarketOrderRepository marketorderrepo;
	
	int x;
	
	@Test
	public void FindPendingOrderSuccessfully() 
	{
	   
	       List<Order> orders = traderpositionrepo.findPendingOrder(1);
	       Order order = orders.get(0);
	       assertThat("currency base should be EUR", Currency.EUR, equalTo(order.getCurrency_base()));
	       assertThat("currency quote should be USD", Currency.USD, equalTo(order.getCurrency_quote()));   
	       assertThat("market order lot size should be 7900", 7900, equalTo(order.getLot_size()));
	       assertThat("side should be BUY", Side.BUY, equalTo(order.getSide()));
	       assertThat("Limit Price s 1.25", 1.414, equalTo(order.getLimit_price()));
	       assertThat("Customer id should be 1",1,equalTo(order.getCust_id()));
	       assertThat("Status should be PENDING",Status.PENDING,equalTo(order.getStatus()));
	 }
	
	@Test
	public void FindCompletedOrderSuccessfully() throws ParseException
	{
		List<Order> orders = traderpositionrepo.findCompletedOrder(1, "2017-07-12", "2017-07-13");
	       Order order = orders.get(0);
	       
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       Date date = new Date();
	       String datee=dateFormat.format(date).toString();
	      
	       assertThat("currency base should be EUR", Currency.EUR, equalTo(order.getCurrency_base()));
	       assertThat("currency quote should be USD", Currency.USD, equalTo(order.getCurrency_quote()));   
	       assertThat("market order lot size should be 900", 900, equalTo(order.getLot_size()));
	       assertThat("side should be BUY", Side.BUY, equalTo(order.getSide()));
	       assertThat("Limit Price s 1.25", 1.414, equalTo(order.getLimit_price()));
	       assertThat("Customer id should be 1",1,equalTo(order.getCust_id()));
	       assertThat("Status should be PENDING",Status.COMPLETED,equalTo(order.getStatus()));
	       assertThat("Date should be 2017-07-13","2017-07-13",equalTo(datee));
	}
	
	
}
