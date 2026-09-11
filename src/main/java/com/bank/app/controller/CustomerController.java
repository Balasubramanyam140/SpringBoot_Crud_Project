package com.bank.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.model.Customer;
import com.bank.app.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
	    this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	    return ResponseEntity.ok(customerService.createCustomer(customer));
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer() {
	    return ResponseEntity.ok(customerService.getAllCustomer());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

	    return customerService.getCustomerById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
	    
		try {
			return ResponseEntity.ok(customerService.updateCustomer(id, customer));
		}
		catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
	    
		try {
			customerService.deleteCustomer(id);
			return ResponseEntity.ok("Customer Deleted");
		}
		catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
	

