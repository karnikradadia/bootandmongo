package com.learning.bootandmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.bootandmongo.dao.MessageDetailStatusDao;
import com.learning.bootandmongo.model.MessageDetailStatus;

@Service
public class MessageDetailStatusService {
	
	@Autowired
	private MessageDetailStatusDao messageDetailStatusDao;
	
	public void saveMessageDetailStatus(MessageDetailStatus messageDetailStatus){
		messageDetailStatusDao.save(messageDetailStatus);
	}
	
	public List<MessageDetailStatus> findAllMessageDetailStatus(){
		return messageDetailStatusDao.findAll();
	}

}
