package com.facturar.app.service;

import com.facturar.app.entity.Country;
import com.facturar.app.repository.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Country> findAll(Pageable pageable) {
        return countryDao.findAll(pageable);
    }
}
