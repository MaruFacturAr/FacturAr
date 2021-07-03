package com.facturar.app.service;

import com.facturar.app.entity.SalesPointEntity;

import java.util.List;
import java.util.Optional;

public interface SalesPointService {
    public Iterable<SalesPointEntity> findAll();

    public Optional<SalesPointEntity> findById(Long id);

    public SalesPointEntity save(SalesPointEntity salesPoint);

    public void deleteById(Long id);

    public List<SalesPointEntity> findAllByUserIdAndCodeOrName(Long userId, String code,
                                                         String name);
    public SalesPointEntity activate(Long id, Long userId);

    public SalesPointEntity deactivate(Long id, Long userId);

    public Iterable<SalesPointEntity> findByUserId(Long userId);
}
