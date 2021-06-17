package com.facturar.app.service;

import com.facturar.app.entity.VoucherType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoucherTypeService {
    public Iterable<VoucherType> findAll();

    public Page<VoucherType> findAll(Pageable pageable);
}
