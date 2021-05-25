package com.facturar.app.service;

import com.facturar.app.entity.Province;
import com.facturar.app.repository.ProvinceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public Iterable<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Province> findAll(Pageable pageable) {
        return provinceDao.findAll(pageable);
    }
}
