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
import com.forex.repository.CancelRequestRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class})

public class CancelRequestTest {
      
        

        @Autowired
        private CancelRequestRepository cq;
        String current_status;
       @Before
       public void cancelRequestTestDatabase()
       {

            current_status = cq.cancelRequest(3);
    
       }
        
        @Test
        public void cancelRequestTest()
        {
                   assertThat("request with id 1 should be cancelled", "Request Cancelled", equalTo(current_status));
        }
   
}