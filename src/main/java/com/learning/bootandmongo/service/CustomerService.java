package com.learning.bootandmongo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.learning.bootandmongo.dao.CustomerDao;
import com.learning.bootandmongo.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public void saveCustomer(Customer customer){
		customerDao.save(customer);
	}
	
	public List<Customer> findAllCustomers(){
		return customerDao.findAll();
	}
	
	public List<Customer> findByFirstName(String firstName){
		List<Customer> customers = new ArrayList<Customer>(); 
		Customer customer = customerDao.findByFirstName(firstName);
		customers.add(customer);
		return customers;
		
	}
	
	public List<Customer> findByLastName(String lastName){
		return customerDao.findByLastName(lastName);
	}
	
	public Customer findOne(Customer customer){
		Example<Customer> example = Example.of(customer);
		Optional<Customer> optional = customerDao.findOne(example);
		return optional.get();
	}
	
	public void deleteCustomer(Customer customer){
		customerDao.delete(customer);
	}
	
}
