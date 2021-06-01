package com.facturar.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facturar.app.entity.CompanyEntity;
import com.facturar.app.entity.UserEntity;

public interface CompanyService {

    public Iterable<CompanyEntity> findAll();

    public Page<CompanyEntity> findAll(Pageable pageable);

    public Optional<CompanyEntity> findById(Long id);

    public CompanyEntity save(CompanyEntity company);

    public void deleteById(Long id);

    public CompanyEntity findByuserId(Long userId);

}
