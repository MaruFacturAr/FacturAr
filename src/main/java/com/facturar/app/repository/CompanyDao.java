package com.facturar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facturar.app.entity.CompanyEntity;

public interface CompanyDao extends JpaRepository<CompanyEntity, Long> {

    CompanyEntity findByuserId(Long userId);
}
