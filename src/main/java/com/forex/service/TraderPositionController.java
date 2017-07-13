package com.forex.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forex.domain.MarketOrder;
import com.forex.domain.Order;
import com.forex.repository.TraderPositionRepository;

@RestController
public class TraderPositionController 
{
	@Autowired
	TraderPositionRepository traderpositionrepo;
	
	@RequestMapping(value = "/order/trader-position-pending/{cust_id}", method = RequestMethod.GET)
	public List<Order> findPendingMarketOrder(@PathVariable("cust_id")int cust_id)
	{	        
		 return traderpositionrepo.findPendingOrder(cust_id);
	}
	
	/*@RequestMapping(value = "/order/trader-position-completed/{cust_id}", method = RequestMethod.GET)
	public List<MarketOrder> findCompletedMarketOrder(@PathVariable("cust_id")int cust_id)
	{	        
		 return traderpositionrepo.findCompletedMarketOrder(cust_id);
	}*/
	
	@RequestMapping(value = "/order/trader-position-completed/{cust_id}/{fromDate}/{toDate}", method = RequestMethod.GET)
	public  List<Order> findCompletedOrder(
																	@PathVariable(value="cust_id") int cust_id,
																	@PathVariable String fromDate,
																	@PathVariable String toDate) throws ParseException
	{
		return traderpositionrepo.findCompletedOrder(cust_id, fromDate, toDate);
	}
}
