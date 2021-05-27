package com.facturar.app.service;

import com.facturar.app.entity.BillingData;
import com.facturar.app.repository.BillingDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingDataServiceImpl implements BillingDataService {
    @Autowired
    private BillingDataDao billingDataDao;

    @Override
    public Iterable<BillingData> findAll() {
        return billingDataDao.findAll();
    }

    @Override
    public Page<BillingData> findAll(Pageable pageable) {
        return billingDataDao.findAll(pageable);
    }

    @Override
    public Optional<BillingData> findById(Long id) {
        return findById(id);
    }

    @Override
    public BillingData save(BillingData billingData) {
        return billingDataDao.save(billingData);
    }

    @Override
    public void deleteById(Long id) {
        billingDataDao.deleteById(id);
    }
}
