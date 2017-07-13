package com.forex.test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forex.Application;
import com.forex.domain.Currency;
import com.forex.domain.MarketOrder;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.repository.MarketOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class})
public class MarketOrderTest 
{
	@Autowired
	MarketOrderRepository marketorderrepo;
	
	int x;
	@Before
	public void setupDataBase()
	{
	       Order marketorder = new Order();
	       marketorder.setCurrency_base(Currency.EUR);
	       marketorder.setCurrency_quote(Currency.USD);
	       marketorder.setLot_size(25);
	       marketorder.setSide(Side.BUY);
	       marketorder.setLimit_price(1.25);
	       
	       x = marketorderrepo.placeMarketOrder(marketorder);
	   }
	   
	   @Test
	   public void insertSingleMarketOrderCompletesSuccessfully() 
	   {
	   
	       Order marketOrder = marketorderrepo.findMarketOrder(x);
	       
	       assertThat("currency base should be EUR", Currency.EUR, equalTo(marketOrder.getCurrency_base()));
	       assertThat("currency quote should be USD", Currency.USD, equalTo(marketOrder.getCurrency_quote()));   
	       assertThat("market order lot size should be 25", 25, equalTo(marketOrder.getLot_size()));
	       assertThat("side should be BUY", Side.BUY, equalTo(marketOrder.getSide()));
	       assertThat("Limit Price s 1.25", 1.25, equalTo(marketOrder.getLimit_price()));
	       
	   }
}
