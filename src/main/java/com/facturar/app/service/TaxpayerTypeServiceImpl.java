package com.facturar.app.service;

import com.facturar.app.entity.TaxpayerType;
import com.facturar.app.repository.TaxpayerTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaxpayerTypeServiceImpl implements TaxpayerTypeService{
    @Autowired
    private TaxpayerTypeDao taxpayerTypeDao;
    @Override
    @Transactional(readOnly = true)
    public Iterable<TaxpayerType> findAll() {
        return taxpayerTypeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxpayerType> findAll(Pageable pageable) {
        return taxpayerTypeDao.findAll(pageable);
    }
}
