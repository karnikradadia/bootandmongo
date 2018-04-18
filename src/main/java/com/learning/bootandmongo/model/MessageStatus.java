package com.learning.bootandmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="message_status")
public class MessageStatus {
	
	@Id
	private String id;
	private InboundCustomerMessage inboundCustomerMessage;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public InboundCustomerMessage getInboundCustomerMessage() {
		return inboundCustomerMessage;
	}
	public void setInboundCustomerMessage(InboundCustomerMessage inboundCustomerMessage) {
		this.inboundCustomerMessage = inboundCustomerMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
