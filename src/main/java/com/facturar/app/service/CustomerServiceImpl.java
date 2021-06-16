package com.facturar.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facturar.app.entity.CustomersEntity;
import com.facturar.app.repository.CustomersDao;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomersDao customersDaoRepository;

	@Override
	public Iterable<CustomersEntity> findAll() {
		return customersDaoRepository.findAll();
	}

	@Override
	public Optional<CustomersEntity> findById(Long id) {
		return customersDaoRepository.findById(id);
	}

	@Override
	public CustomersEntity save(CustomersEntity customer) {
		return customersDaoRepository.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		customersDaoRepository.deleteById(id);
	}

}
