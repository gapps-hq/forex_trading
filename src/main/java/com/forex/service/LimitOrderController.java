package com.forex.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forex.domain.LimitOrder;

@RestController
public class LimitOrderController {
	@RequestMapping(value = "/order/limit_order/save", method = RequestMethod.POST)
	public String saveLimitOrder(@RequestBody LimitOrder limitOrder){
		if(limitOrder.getCurrency() == null || limitOrder.getLot_size() == 0){
			//error message
			return "Please enter currency or amount";
	
		}
		//insert limit order
		int orderId = 10;
		return "Your order has been successfully placed. The Order ID is " + orderId;
	}

	@RequestMapping(value = "/order/limit_order/{orderId}", method = RequestMethod.GET)
	public String getLimitOrder(@PathVariable("orderId")int orderId){
//	public LimitOrder getLimitOrder(@PathVariable("orderId")int orderId){

		return "get one";
	}
	
	@RequestMapping(value = "/order/limit_order/get_all", method = RequestMethod.GET)
	public String getAllOrders(){
//	public List<LimitOrder> getAllOrders(){

		return "get all";
		
	}
}
