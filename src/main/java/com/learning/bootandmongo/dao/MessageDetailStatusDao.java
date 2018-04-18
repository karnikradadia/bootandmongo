package com.learning.bootandmongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learning.bootandmongo.model.MessageDetailStatus;

@Repository
public interface MessageDetailStatusDao extends MongoRepository<MessageDetailStatus, String> {

}
