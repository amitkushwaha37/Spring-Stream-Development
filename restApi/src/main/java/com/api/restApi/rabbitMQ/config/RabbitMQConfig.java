package com.api.restApi.rabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
Marks this class as Spring configuration class
Spring will create beans defined here
*/
@Configuration
public class RabbitMQConfig {

	/*
	 * Queue name where messages will be stored
	 */
	public static final String QUEUE = "employee_queue";

	/*
	 * Exchange routes messages to queue
	 */
	public static final String EXCHANGE = "employee_exchange";

	/*
	 * Routing key helps exchange decide where message goes
	 */
	public static final String ROUTING_KEY = "employee_routing_key";

	/*
	 * Create Queue bean
	 */
	@Bean
	public Queue queue() {

		// Queue name
		return new Queue(QUEUE);
	}

	/*
	 * Create Exchange bean
	 */
	@Bean
	public TopicExchange exchange() {

		return new TopicExchange(EXCHANGE);
	}

	/*
	 * Bind queue with exchange using routing key
	 */
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {

		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
}
