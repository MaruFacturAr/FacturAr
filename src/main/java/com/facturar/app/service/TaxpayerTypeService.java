package com.facturar.app.service;

import com.facturar.app.entity.TaxpayerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxpayerTypeService {
    public Iterable<TaxpayerType> findAll();

    public Page<TaxpayerType> findAll(Pageable pageable);
}
