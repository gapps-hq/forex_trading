package com.forex.order.limit.test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forex.Application;
import com.forex.domain.Currency;
import com.forex.domain.LimitOrder;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.domain.TypeOfOrder;
import com.forex.repository.LimitOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class})
public class TestLimitOrder {

	private int x;
    @Autowired
    private LimitOrderRepository limitOrderRepository;
    
    @Before
    public void setupDataBase(){
    	
//    	order_id INT NOT NULL AUTO_INCREMENT,
//    	cust_id INT NOT NULL,
//    	currency_base VARCHAR(16) NOT NULL,
//    	currency_quote VARCHAR(16) NOT NULL,
//    	price DOUBLE DEFAULT NULL,
//    	lot_size INT NOT NULL,
//    	transaction_time TIMESTAMP NOT NULL,
//    	type_of_order VARCHAR(16),
//    	side VARCHAR(16) NOT NULL,
//    	limit_price DOUBLE DEFAULT NULL,
//    	status VARCHAR(20) NOT NULL,
        LimitOrder limitOrder = new LimitOrder();
        limitOrder.setCust_id(1);
        limitOrder.setCurrency_base(Currency.EUR);
        limitOrder.setCurrency_quote(Currency.USD);
        limitOrder.setPrice(1.3);
        limitOrder.setLot_size(100);
        limitOrder.setTransaction_time(new Timestamp(System.currentTimeMillis()));
        limitOrder.setType_of_order(TypeOfOrder.LIMIT);
        limitOrder.setSide(Side.BUY);
        limitOrder.setStatus(Status.PENDING);
        limitOrder.setSide(Side.SELL);
        limitOrder.setLimit_price(1.2);

        x = limitOrderRepository.saveOrder(limitOrder);
    }
    
    @Test
    public void insertSingleMarketOrderCompletesSuccessfully() {
    
        LimitOrder limitOrders = limitOrderRepository.findOrder(x);
        assertThat("market order size should be 100", 100, equalTo(limitOrders.getLot_size()));
        assertThat("currency base should be EUR", Currency.EUR, equalTo(limitOrders.getCurrency_base()));
        assertThat("Limit Price is 1.2", 1.2, equalTo(limitOrders.getLimit_price()));
        assertThat("side should be SELL", Side.SELL, equalTo(limitOrders.getSide()));
    }

}