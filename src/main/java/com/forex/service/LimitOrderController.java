package com.forex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forex.domain.LimitOrder;
import com.forex.repository.LimitOrderRepository;

@RestController
public class LimitOrderController {    
    @Autowired
    private LimitOrderRepository loRepo;
    @RequestMapping(value = "/order/limit_order/save", method = RequestMethod.POST)
    public String saveLimitOrder(@RequestBody LimitOrder limitOrder){
        if(limitOrder.getCurrency_base() == null || limitOrder.getCurrency_quote() == null || limitOrder.getLot_size() == 0){
            //error message
            return "Please enter currency or amount";
    
        }
        //insert limit order
        int orderId = loRepo.saveOrder(limitOrder);
        return "Your order has been successfully placed. The Order ID is " + orderId;
    }

    @RequestMapping(value = "/order/limit_order/{orderId}", method = RequestMethod.GET)
    public LimitOrder getLimitOrder(@PathVariable("orderId")int orderId){

        return loRepo.findOrder(orderId);
    }
    
    @RequestMapping(value = "/order/limit_order/get_all", method = RequestMethod.GET)
    public String getAllOrders(){
//    public List<LimitOrder> getAllOrders(){
    	return "getall";
    }
}