package com.facturar.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facturar.app.entity.CustomersEntity;
import com.facturar.app.repository.CustomersDao;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional(readOnly = true)
	public List<CustomersEntity> findAllByUserIdAndCodeOrName(Long userId, String code, String name) {
		return customersDaoRepository.findAllByUserIdAndCodeOrName(userId, code, name);
	}

	@Override
	public CustomersEntity activate(Long id, Long userId) {
		Optional<CustomersEntity> customersEntity = customersDaoRepository.findByIdAndUserId(id, userId);
		customersEntity.get().setStatus_id(1);
		return customersEntity.get();
	}

	@Override
	public CustomersEntity deactivate(Long id, Long userId) {
		Optional<CustomersEntity> customersEntity = customersDaoRepository.findByIdAndUserId(id, userId);
		customersEntity.get().setStatus_id(2);
		return customersEntity.get();
	}

}
