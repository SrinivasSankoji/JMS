package com;


import java.util.Hashtable;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSProducer {

	public static void main(String[] args) throws Exception {
		
		InitialContext initialContext = new InitialContext();

		Queue queue = (Queue) initialContext.lookup("queue/queue0");

		QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) initialContext
				.lookup("queue/connectionFactory");
		
		QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();

		QueueSession queueSession = queueConnection.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

		QueueSender queueSender = queueSession.createSender(queue);
		

		queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		TextMessage textMessage = queueSession.createTextMessage("Hello World");

		queueSender.send(textMessage);

		System.out.println("Message Sent: " + textMessage.getText());

		queueConnection.close();

	}

}
