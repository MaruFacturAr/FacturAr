package com.facturar.app.service;

import com.facturar.app.entity.BillingData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BillingDataService {
    public Iterable<BillingData> findAll();

    public Page<BillingData> findAll(Pageable pageable);

    public Optional<BillingData> findById(Long id);

    public BillingData save(BillingData billingData);

    public void deleteById(Long id);
}
