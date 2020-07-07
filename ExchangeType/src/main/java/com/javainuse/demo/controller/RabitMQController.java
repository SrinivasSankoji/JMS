package com.javainuse.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabitmq")
@CrossOrigin(maxAge = 6000)
public class RabitMQController {

	@Autowired
	public AmqpTemplate amqpTemplate;

	@GetMapping("/direct")
	public String directExchange(@RequestParam("exchange") String exchange,
			@RequestParam("routingKey") String routingKey, @RequestParam("message") String message) {
		amqpTemplate.convertAndSend(exchange, routingKey, message);
		return "Message Sent through Direct Exchange";
	}

	@GetMapping("/fanout")
	public String fanoutExchange(@RequestParam("exchange") String exchange, @RequestParam("message") String message) {
		amqpTemplate.convertAndSend(exchange, "", message);
		return "Message Sent through Fanout Exchange";
	}

	@GetMapping("/topic")
	public String topicExchange(@RequestParam("exchange") String exchange,
			@RequestParam("routingKey") String routingKey, @RequestParam("message") String message) {
		amqpTemplate.convertAndSend(exchange,routingKey, message);
		return "Message Sent through Topic Exchange";
	}
	
	@GetMapping("/header")
	public String headerExchange(@RequestParam("exchange") String exchange,
			@RequestParam("department") String department, @RequestParam("message") String message) {
		MessageProperties messageProperties=new MessageProperties();
		messageProperties.setHeader("department", department);
		MessageConverter messageConverter=new SimpleMessageConverter();
		Message message1=messageConverter.toMessage(message, messageProperties);
		amqpTemplate.convertAndSend(exchange,"", message1);
		return "Message Sent through Header Exchange";
	}
}
