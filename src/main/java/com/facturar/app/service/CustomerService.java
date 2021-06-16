package com.facturar.app.service;

import java.util.Optional;

import com.facturar.app.entity.CustomersEntity;

public interface CustomerService {

	public Iterable<CustomersEntity> findAll();

    public Optional<CustomersEntity> findById(Long id);

    public CustomersEntity save(CustomersEntity customer);

    public void deleteById(Long id);
	
}
