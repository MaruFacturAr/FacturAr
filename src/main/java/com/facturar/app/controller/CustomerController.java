package com.facturar.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.facturar.app.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facturar.app.entity.CustomersEntity;
import com.facturar.app.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

    @Autowired
    private SecurityUtil securityUtil;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomersEntity customersEntity) {
        Long userId = securityUtil.getCurrentUserId();
        customersEntity.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customersEntity));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody CustomersEntity customersEntity) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.activate(customersEntity.getId(),userId));
    }

    @PostMapping("/deactivate")
    public ResponseEntity<?> deactivate(@RequestBody CustomersEntity customersEntity) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.deactivate(customersEntity.getId(),userId));
    }


    // Read customers
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long customerId) {
		
		Optional<CustomersEntity> customer = customerService.findById(customerId);
		
		if (!customer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(customer);
	}


    @GetMapping("/find")
    public ResponseEntity<?> findAllCodeOrName(@RequestParam(required = false) String code, @RequestParam(required = false) String name ) {

        Long userId = securityUtil.getCurrentUserId();
        List<CustomersEntity> item = customerService.findAllByUserIdAndCodeOrName(userId, code, name);

        return ResponseEntity.ok(item);
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
        Long userId = securityUtil.getCurrentUserId();
        List<CustomersEntity> customers = StreamSupport
                .stream(customerService.findByUserId(userId).spliterator(), false)
                .collect(Collectors.toList());

        return customers;

    }
    
	
	

}
