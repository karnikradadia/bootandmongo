package com.learning.bootandmongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learning.bootandmongo.model.MessageStatus;

@Repository
public interface MessageStatusDao extends MongoRepository<MessageStatus, String> {

}
