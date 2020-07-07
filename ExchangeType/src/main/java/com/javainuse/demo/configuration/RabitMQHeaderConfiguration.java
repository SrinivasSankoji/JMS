package com.javainuse.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQHeaderConfiguration {

	@Autowired
	public Queue adminQueue;

	@Autowired
	public Queue financeQueue;

	@Autowired
	public Queue marketingQueue;

	@Bean
	public HeadersExchange headerExchange() {
		return new HeadersExchange("header-exchange");
	}

	@Bean
	public Binding adminHeaderBinding(Queue adminQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(adminQueue).to(headerExchange).where("department").matches("admin");
	}

	@Bean
	public Binding financeHeaderBinding(Queue financeQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(financeQueue).to(headerExchange).where("department").matches("finance");
	}

	@Bean
	public Binding marketingHeaderBinding(Queue financeQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(financeQueue).to(headerExchange).where("department").matches("marketing");
	}

}
