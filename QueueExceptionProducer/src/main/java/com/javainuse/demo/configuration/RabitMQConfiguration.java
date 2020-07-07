package com.javainuse.demo.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQConfiguration {

	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange("deadLetterExchange");
	}

	@Bean
	DirectExchange javainuseExchange() {
		return new DirectExchange("javainuseExchange");
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable("deadLetter").build();
	}

	@Bean
	Queue javainuseQueue() {
		return QueueBuilder.durable("javainuseQueue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
				.withArgument("x-dead-letter-routing-key", "deadLetter").build();
	}

	@Bean
	Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetter");
	}

	@Bean
	Binding javainuseBinding() {
		return BindingBuilder.bind(javainuseQueue()).to(javainuseExchange()).with("javainuse");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}
	
	@Bean
	public AmqpTemplate rabbitTemplate() {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
