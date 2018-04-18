package com.learning.bootandmongo.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
	
	
	@Autowired
    private JmsTemplate jmsTemplate;
 
    public void sendMessage(final String inboundCustomerMessage) {
 
        jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				logger.debug("Posting message to JMS Queue. [" + inboundCustomerMessage + "]");
				return session.createTextMessage(inboundCustomerMessage);
			}
		});
    }
}
