package com.facturar.app.service;

import com.facturar.app.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProvinceService {

    public Iterable<Province> findAll();

    public Page<Province> findAll(Pageable pageable);
}
