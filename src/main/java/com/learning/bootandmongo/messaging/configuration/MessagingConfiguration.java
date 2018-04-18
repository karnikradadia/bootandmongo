package com.learning.bootandmongo.messaging.configuration;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {
	
	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	
	private static final String CUSTOMER_QUEUE = "customer-queue";

	@Bean
	public CachingConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory activeMQconnectionFactory = new ActiveMQConnectionFactory();
		activeMQconnectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		activeMQconnectionFactory.setTrustedPackages(Arrays.asList("com.verizon.assignment"));
		SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(activeMQconnectionFactory);
		singleConnectionFactory.setReconnectOnException(true);
		CachingConnectionFactory cachingConnectionFactor = new CachingConnectionFactory(singleConnectionFactory);
		cachingConnectionFactor.setSessionCacheSize(10);
		return cachingConnectionFactor;
	}
	
	@Bean 
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(CUSTOMER_QUEUE);
		return template;
	}
	
}
