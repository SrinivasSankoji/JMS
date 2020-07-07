package com.javainuse.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQDirectConfiguration {

	@Bean
	public Queue adminQueue() {
		return new Queue("admin", true);
	}

	@Bean
	public Queue financeQueue() {
		return new Queue("finance", true);
	}

	@Bean
	public Queue marketingQueue() {
		return new Queue("marketing", true);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("direct-exchange");
	}
	
	@Bean
	public Binding adminBinding(Queue adminQueue,DirectExchange exchange)
	{
		return BindingBuilder.bind(adminQueue).to(exchange).with("admin");
	}
	
	@Bean
	public Binding financeBinding(Queue financeQueue,DirectExchange exchange)
	{
		return BindingBuilder.bind(financeQueue).to(exchange).with("finance");
	}
	
	@Bean
	public Binding marketingBinding(Queue financeQueue,DirectExchange exchange)
	{
		return BindingBuilder.bind(financeQueue).to(exchange).with("marketing");
	}

}
