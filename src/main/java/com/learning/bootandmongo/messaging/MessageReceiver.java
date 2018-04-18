package com.learning.bootandmongo.messaging;

import java.util.UUID;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.bootandmongo.model.Customer;
import com.learning.bootandmongo.model.InboundCustomerMessage;
import com.learning.bootandmongo.model.MessageDetailStatus;
import com.learning.bootandmongo.model.MessageStatus;
import com.learning.bootandmongo.service.CustomerService;
import com.learning.bootandmongo.service.MessageDetailStatusService;
import com.learning.bootandmongo.service.MessageStatusService;

@Component
public class MessageReceiver {
	
	static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	 
    private static final String CUSTOMER_QUEUE = "customer-queue";
     
    @Autowired
    private MessageDetailStatusService messageDetailStatusService;
    @Autowired
    private MessageStatusService messageStatusService;
    @Autowired
    private CustomerService customerService;
     
     
    @JmsListener(destination = CUSTOMER_QUEUE)
    public void receiveMessage(final Message<String> message) throws JMSException {
         
        String jsonMsg = message.getPayload();
        logger.debug("Application : message received : " + jsonMsg);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
			InboundCustomerMessage inboundCustomerMessage = mapper.readValue(jsonMsg, InboundCustomerMessage.class);
			inboundCustomerMessage.setMessageId(UUID.randomUUID().toString());
			
			MessageStatus messageStatus = new MessageStatus();
			messageStatus.setInboundCustomerMessage(inboundCustomerMessage);
			messageStatus.setStatus("RECEIVED");
			
			messageStatus = messageStatusService.saveMessageStatus(messageStatus);
			logger.debug("Message status saved for message " + inboundCustomerMessage.getMessageId());
			
			for(Customer customer: inboundCustomerMessage.getCustomers()){
				MessageDetailStatus messageDetailStatus = new MessageDetailStatus();
				if("SAVE".equals(inboundCustomerMessage.getOperation())){
					customerService.saveCustomer(customer);
					logger.debug("Customer Successfully Saved. " + customer.toString());
					messageDetailStatus.setCustomerId(customer.getId());
					messageDetailStatus.setMessageId(inboundCustomerMessage.getMessageId());
					messageDetailStatus.setStatus("CUSTOMER SAVED");
					messageDetailStatusService.saveMessageDetailStatus(messageDetailStatus);
					logger.debug("Message Status saved successfully. " + messageDetailStatus.toString());
				}
				else if("DELETE".equals(inboundCustomerMessage.getOperation())){
					customerService.deleteCustomer(customer);
					logger.debug("Customer Successfully Saved. " + customer.toString());
					messageDetailStatus.setCustomerId(customer.getId());
					messageDetailStatus.setMessageId(inboundCustomerMessage.getMessageId());
					messageDetailStatus.setStatus("CUSTOMER DELETED");
					messageDetailStatusService.saveMessageDetailStatus(messageDetailStatus);
					logger.debug("Message Status saved successfully. " + messageDetailStatus.toString());
				}
			}
			messageStatus.setStatus("COMPLETED");
			messageStatusService.saveMessageStatus(messageStatus);
			
		} catch (Exception e) {
			logger.debug("Error processing Inbound message.", e);
		}
         
    }

}
