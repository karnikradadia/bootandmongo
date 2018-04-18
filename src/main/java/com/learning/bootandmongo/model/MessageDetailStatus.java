package com.learning.bootandmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="message_detail_status")
public class MessageDetailStatus {
	
	@Id
	private String id;
	private String messageId;
	private String customerId;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
    public String toString() {
        return String.format(
                "MessageStatus[id=%s, customerId='%s', status='%s']",
                id, customerId, status);
    }
	
	
	
}
