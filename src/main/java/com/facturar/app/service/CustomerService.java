package com.facturar.app.service;

import java.util.List;
import java.util.Optional;

import com.facturar.app.entity.CustomersEntity;

public interface CustomerService {

	public Iterable<CustomersEntity> findAll();

    public Optional<CustomersEntity> findById(Long id);

    public CustomersEntity save(CustomersEntity customer);

    public void deleteById(Long id);

    public List<CustomersEntity> findAllByUserIdAndCodeOrName(Long userId, String code,
                                                                String name);

    public CustomersEntity activate(Long id, Long userId);

    public CustomersEntity deactivate(Long id, Long userId);

    public Iterable<CustomersEntity> findByUserId(Long userId);
	
}
