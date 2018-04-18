package com.learning.bootandmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.bootandmongo.dao.MessageStatusDao;
import com.learning.bootandmongo.model.MessageStatus;

@Service
public class MessageStatusService {
	
	@Autowired
	private MessageStatusDao messageStatusDao;
	
	public MessageStatus saveMessageStatus(MessageStatus messageStatus){
		return messageStatusDao.save(messageStatus);
	}
	
	public List<MessageStatus> findAllMessageStatus(){
		return messageStatusDao.findAll();
	}

}
