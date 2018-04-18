package com.learning.bootandmongo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.bootandmongo.model.Customer;
import com.learning.bootandmongo.model.Response;
import com.learning.bootandmongo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(path="/findAll", method=RequestMethod.GET)
	public @ResponseBody String findAllCustomers(){
		StopWatch sw = new StopWatch();
		sw.start();
		List<Customer> customers = null;
		Response response = new Response();
		String reponseJson = "";
		
		try{
			customers = customerService.findAllCustomers();
			if(customers.size() > 0){
				response.setStatus("SUCCESS");
			}
			else{
				response.setStatus("DATA NOT FOUND");
			}
			response.setCustomers(customers);
		} catch (Exception e){
			logger.error("Error while fetching data", e);
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
		}
		
		try {
			reponseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting java object to json", e);
		}
		sw.stop();
		logger.debug("FindAll operation completed. Total time taken " + sw.getTotalTimeMillis() + " ms.");
		
		return reponseJson;
		
	}
	
	@RequestMapping(path="/addCustomer", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addCustomer(@RequestBody String jsonString){
		StopWatch sw = new StopWatch();
		sw.start();
		Customer customer = null;
		Response response = new Response();
		String reponseJson = "";
		try {
			customer = mapper.readValue(jsonString, Customer.class);
			customerService.saveCustomer(customer);
			response.setStatus("SUCCESS");
			
		} catch (Exception e) {
			logger.error("Error while saving data", e);
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
		}
		
		try {
			reponseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting java object to json", e);
		}
		
		sw.stop();
		logger.debug("addCustomer operation completed. Total time taken " + sw.getTotalTimeMillis() + " ms.");
		
		return reponseJson;
	}
	
	@RequestMapping(path="/findCustomerByFirstName", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findByFirstName(@RequestBody String jsonString){
		StopWatch sw = new StopWatch();
		sw.start();
		Customer customer = null;
		List<Customer> customers = null;
		Response response = new Response();
		String reponseJson = "";
		try {
			customer = mapper.readValue(jsonString, Customer.class);
			customers = customerService.findByFirstName(customer.getFirstName());
			response.setStatus("SUCCESS");
			response.setCustomers(customers);
			
		} catch (Exception e) {
			logger.error("Error while finding customer data", e);
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
		}
		
		try {
			reponseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting java object to json", e);
		}
		
		sw.stop();
		logger.debug("findCustomerByFirstName operation completed. Total time taken " + sw.getTotalTimeMillis() + " ms.");
		
		return reponseJson;
	}
	
	@RequestMapping(path="/findCustomerByLastName", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findByLastName(@RequestBody String jsonString){
		StopWatch sw = new StopWatch();
		sw.start();
		Customer customer = null;
		List<Customer> customers = null;
		Response response = new Response();
		String reponseJson = "";
		try {
			customer = mapper.readValue(jsonString, Customer.class);
			customers = customerService.findByLastName(customer.getLastName());
			response.setStatus("SUCCESS");
			response.setCustomers(customers);
			
		} catch (Exception e) {
			logger.error("Error while finding customer data", e);
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
		}
		
		try {
			reponseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting java object to json", e);
		}
		
		sw.stop();
		logger.debug("findCustomerByLastName operation completed. Total time taken " + sw.getTotalTimeMillis() + " ms.");
		
		return reponseJson;
	}
	
	@RequestMapping(path="/deleteCustomer", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteCustomer(@RequestBody String jsonString){
		StopWatch sw = new StopWatch();
		sw.start();
		Customer customer = null;
		Response response = new Response();
		String reponseJson = "";
		try {
			Customer input = mapper.readValue(jsonString, Customer.class);
			customer = customerService.findOne(input);
			if(customer != null){
				customerService.deleteCustomer(customer);
				response.setStatus("SUCCESS");
			}
			else{
				response.setStatus("DATA NOT FOUND");
			}
			
			
		} catch (Exception e) {
			logger.error("Error while deleting customer data", e);
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
		}
		
		try {
			reponseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting java object to json", e);
		}
		
		sw.stop();
		logger.debug("deleteCustomer operation completed. Total time taken " + sw.getTotalTimeMillis() + " ms.");
		
		return reponseJson;
	}
	
	@RequestMapping(path="/updateCustomerLastName", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateCustomerLastName(@RequestBody String jsonString){
		StopWatch sw = new StopWatch();
		sw.start();
		Customer customer = null;
		Response response = new Response();
		String reponseJson = "";
		try {
			Customer input = mapper.readValue(jsonString, Customer.class);
			customer = customerService.findByFirstName(input.getFirstName()).get(0);
			if(customer == null){
				response.setStatus("DATA NOT FOUND");
			}
			else{
				customer.setLastName(input.getLastName());
				customerService.saveCustomer(customer);
				response.setStatus("SUCCESS");
			}
			
		} catch (Exception e) {
			logger.error("Error while updating customer data", e);
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
		}
		
		try {
			reponseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting java object to json", e);
		}
		
		sw.stop();
		logger.debug("updateCustomerLastName operation completed. Total time taken " + sw.getTotalTimeMillis() + " ms.");
		
		return reponseJson;
	}
	

}
