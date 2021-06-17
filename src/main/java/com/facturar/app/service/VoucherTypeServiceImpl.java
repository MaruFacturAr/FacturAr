package com.facturar.app.service;

import com.facturar.app.entity.VoucherType;
import com.facturar.app.repository.VoucherTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoucherTypeServiceImpl implements  VoucherTypeService{

    @Autowired
    private VoucherTypeDao voucherTypeDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<VoucherType> findAll() {
        return voucherTypeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VoucherType> findAll(Pageable pageable) {
        return voucherTypeDao.findAll(pageable);
    }
}
