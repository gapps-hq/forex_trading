package com.forex.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forex.repository.CancelRequestRepository;



@RestController
public class CancelController {
    @Autowired
    CancelRequestRepository cq;
    
    @RequestMapping(value = "/order/cancel/{order_id}", method = RequestMethod.GET)
    public String cancelRequest(@PathVariable("order_id") int order_id){
        return cq.cancelRequest(order_id);
        
    }
    

}