package com.bank.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public Customer createCustomer(Customer customer) {
    	return customerRepository.save(customer);
    }
    
    public List<Customer> getAllCustomer(){
    	return customerRepository.findAll();
    }
    
    public Optional<Customer> getCustomerById(Long id){
    	return customerRepository.findById(id);
    }
    
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        return customerRepository.findById(id)
                .map(existing -> {

                    existing.setFirstName(updatedCustomer.getFirstName());
                    existing.setLastName(updatedCustomer.getLastName());
                    existing.setEmail(updatedCustomer.getEmail());
                    existing.setPhoneNumber(updatedCustomer.getPhoneNumber());

                    return customerRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Customer Not Found with id "+ id));
    }
    
    public void deleteCustomer(Long id) {
    	if(!customerRepository.existsById(id)) {
    		throw new RuntimeException("Customer Not Found with id "+ id);
    	}
    	customerRepository.deleteById(id);
    }
	
}
