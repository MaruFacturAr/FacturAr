package com.facturar.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facturar.app.entity.CompanyEntity;
import com.facturar.app.repository.CompanyDao;

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDaoRepository;

    @Override
    public Iterable<CompanyEntity> findAll() {
        return companyDaoRepository.findAll();
    }

    @Override
    public Page<CompanyEntity> findAll(Pageable pageable) {
        return companyDaoRepository.findAll(pageable);
    }

    @Override
    public Optional<CompanyEntity> findById(Long id) {
        return companyDaoRepository.findById(id);
    }

    @Override
    public CompanyEntity save(CompanyEntity company) {
        return companyDaoRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {
        companyDaoRepository.deleteById(id);
    }

}
