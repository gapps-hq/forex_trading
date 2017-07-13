package com.forex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forex.domain.Order;
import com.forex.repository.MarketOrderRepository;
import com.forex.repository.OrderMatchingRepository;

@RestController
public class MarketOrderController 
{
	@Autowired
	MarketOrderRepository marketorderrepo;
	
	@Autowired
    OrderMatchingRepository orderRepo;
	
	@RequestMapping("order/marketorder")
	public List<Order> findAll()
	{
		return marketorderrepo.findAll();
	}
	
	@RequestMapping(value = "/order/marketorder/{order_id}", method = RequestMethod.GET)
	public Order findMarketOrder(@PathVariable("order_id")int order_id)
	{	        
		 return marketorderrepo.findMarketOrder(order_id);
	}
	
	
	@RequestMapping(value="/order/marketorder/place",
			method=RequestMethod.POST)
	public String placeMarketOrder(@RequestBody Order marketorder){
        if(marketorder.getCurrency_base() == null || marketorder.getCurrency_quote() == null || marketorder.getLot_size() == 0){
            //error message
            return "Please enter currency or amount";
    
        }
        //insert limit order
        int orderId = marketorderrepo.placeMarketOrder(marketorder);
        
        orderRepo.matchOrder();
        return "Your order has been successfully placed. The Order ID is " + orderId;
    }
	
}
