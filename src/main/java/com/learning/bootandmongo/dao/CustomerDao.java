package com.learning.bootandmongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learning.bootandmongo.model.Customer;

@Repository
public interface CustomerDao extends MongoRepository<Customer, String>{
	
	public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
	
}
