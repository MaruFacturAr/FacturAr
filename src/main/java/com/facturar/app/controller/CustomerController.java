package com.facturar.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.entity.CustomersEntity;
import com.facturar.app.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// Read customers
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long customerId) {
		
		Optional<CustomersEntity> customer = customerService.findById(customerId);
		
		if (!customer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(customer);
	}
	
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long customerId) {

        if (!customerService.findById(customerId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        customerService.deleteById(customerId);

        return new ResponseEntity<>("Se ha eliminado el usuario con id: " + customerId, HttpStatus.OK);
    }
    
    //ReadAll
    @GetMapping
    public List<CustomersEntity> readAll(){

        List<CustomersEntity> customers = StreamSupport
                .stream(customerService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return customers;

    }
    
	
	

}
