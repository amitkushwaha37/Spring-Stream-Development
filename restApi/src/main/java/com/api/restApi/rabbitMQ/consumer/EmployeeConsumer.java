package com.api.restApi.rabbitMQ.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.api.restApi.rabbitMQ.config.RabbitMQConfig;



/*
@Component
Spring will manage this class
*/
@Component
public class EmployeeConsumer {

	 /*
    @RabbitListener
    Listens to RabbitMQ queue
    Whenever message arrives → this method executes
    */
   @RabbitListener(queues = RabbitMQConfig.QUEUE)
   public void receiveEmployeeMessage(String message) {

       /*
        Business logic after receiving message
        */
       System.out.println("Received Message From Queue: " + message);

   }
}
