package com.api.restApi.rabbitMQ.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restApi.rabbitMQ.config.RabbitMQConfig;


/*
@Service
Spring will create bean for this class
*/
@Service
public class EmployeeProducer {

	/*
	 * RabbitTemplate helps send messages to RabbitMQ
	 */
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/*
	 * Method to send message to RabbitMQ
	 */
	/*
	 * Method to send message to RabbitMQ
	 */
	public void sendEmployeeCreatedMessage(String message) {

		/*
		 * convertAndSend() Sends message to exchange using routing key
		 */
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
	}
}