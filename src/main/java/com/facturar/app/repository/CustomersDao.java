package com.facturar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturar.app.entity.CustomersEntity;

@Repository
public interface CustomersDao extends JpaRepository<CustomersEntity, Long> {
	
}
