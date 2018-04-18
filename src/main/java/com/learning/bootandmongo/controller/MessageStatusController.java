package com.learning.bootandmongo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.bootandmongo.messaging.MessageSender;
import com.learning.bootandmongo.service.MessageDetailStatusService;
import com.learning.bootandmongo.service.MessageStatusService;

@RestController
@RequestMapping("/message")
public class MessageStatusController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageStatusController.class);
	
	@Autowired
	private MessageSender messageSender;
	@Autowired
	private MessageStatusService messageStatusService;
	@Autowired
	private MessageDetailStatusService messageDetailStatusService;

	@RequestMapping(path="/postMessage", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void postJmsMessage(@RequestBody String jsonMsg){
		
		messageSender.sendMessage(jsonMsg);
		logger.debug("Message Successfully posted to JMS Queue. [" + jsonMsg + "]");
		
	}
	
	@RequestMapping(path="/findAllMessageStatus", method=RequestMethod.GET)
	public @ResponseBody String findAllMessageStatus(){
		ObjectMapper mapper = new ObjectMapper();
		String jsonMsg = null;
		try {
			jsonMsg = mapper.writeValueAsString(messageStatusService.findAllMessageStatus());
		} catch (JsonProcessingException e) {
			logger.error("Error reading message status", e);
		}
		return jsonMsg;
	}
	
	@RequestMapping(path="/findAllMessageDetailStatus", method=RequestMethod.GET)
	public @ResponseBody String findAllMessageDetailStatus(){
		ObjectMapper mapper = new ObjectMapper();
		String jsonMsg = null;
		try {
			jsonMsg = mapper.writeValueAsString(messageDetailStatusService.findAllMessageDetailStatus());
		} catch (JsonProcessingException e) {
			logger.error("Error reading message detail status", e);
		}
		return jsonMsg;
	}
}
