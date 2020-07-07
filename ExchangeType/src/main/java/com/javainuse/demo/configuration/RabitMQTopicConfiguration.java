package com.javainuse.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQTopicConfiguration {

	@Autowired
	public Queue adminQueue;

	@Autowired
	public Queue financeQueue;

	@Autowired
	public Queue marketingQueue;

	@Bean
	public Queue allQueue() {
		return new Queue("allQueue", true);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange("topic-exchange");
	}

	@Bean
	public Binding adminTopicBinding(Queue adminQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(adminQueue).to(topicExchange).with("queue.admin");
	}

	@Bean
	public Binding financeTopicBinding(Queue financeQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(financeQueue).to(topicExchange).with("queue.finance");
	}

	@Bean
	public Binding marketingTopicBinding(Queue financeQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(financeQueue).to(topicExchange).with("queue.marketing");
	}

	@Bean
	Binding allBinding(Queue allQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
	}

}
