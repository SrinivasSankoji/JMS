package com.howtodoinjava.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.howtodoinjava.demo.service.RabbitMQListner;

@Configuration
public class RabitMQConfiguration 
{
	@Value("${javainuse.rabbitmq.queue}")
	String queueName;

	@Value("${javainuse.rabbitmq.exchange}")
	String exchange;

	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;
	
	@Autowired
	RabbitMQListner rabbitMQListner;
	
	@Bean
	public Queue getQueue()
	{
		return new Queue(queueName, true);
	}
	
	@Bean
	public DirectExchange getExchange()
	{
		return new DirectExchange(exchange);
	}
	
	@Bean
	public Binding bindingWithQueue()
	{
		return BindingBuilder.bind(getQueue()).to(getExchange()).with(routingkey);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	/**
	 * Create Custom Connection Factory
	 * @return ConnectionFactory
	 */
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
	public MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(getQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
		return simpleMessageListenerContainer;

	}

}
