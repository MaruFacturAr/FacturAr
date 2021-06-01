package com.facturar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturar.app.entity.CompanyEntity;

@Repository
public interface CompanyDao extends JpaRepository<CompanyEntity, Long> {

    CompanyEntity findByuserId(Long userId);
}
