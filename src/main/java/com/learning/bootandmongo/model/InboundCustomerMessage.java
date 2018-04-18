package com.learning.bootandmongo.model;

import java.util.List;

public class InboundCustomerMessage {
	
	private String messageId;
	private String operation;
	private List<Customer> customers;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	

}
