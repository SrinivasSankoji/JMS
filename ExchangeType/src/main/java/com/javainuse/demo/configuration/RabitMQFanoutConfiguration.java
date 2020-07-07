package com.javainuse.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQFanoutConfiguration {

	@Autowired
	public Queue adminQueue;
	
	@Autowired
	public Queue financeQueue;
	
	@Autowired
	public Queue marketingQueue;
	
	@Bean
	public FanoutExchange fanoutExchange()
	{
		return new FanoutExchange("fanout-exchange");
	}
	
	@Bean
	public Binding adminFanoutBinding(Queue adminQueue,FanoutExchange fanoutExchange)
	{
		return BindingBuilder.bind(adminQueue).to(fanoutExchange);
	}
	
	@Bean
	public Binding financeFanoutBinding(Queue financeQueue,FanoutExchange fanoutExchange)
	{
		return BindingBuilder.bind(financeQueue).to(fanoutExchange);
	}
	
	@Bean
	public Binding marketingFanoutBinding(Queue financeQueue,FanoutExchange fanoutExchange)
	{
		return BindingBuilder.bind(financeQueue).to(fanoutExchange);
	}
}
