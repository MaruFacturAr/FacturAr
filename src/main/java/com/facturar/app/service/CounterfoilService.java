package com.facturar.app.service;

import com.facturar.app.entity.CounterfoilEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CounterfoilService {
    public Iterable<CounterfoilEntity> findAll();

    public Page<CounterfoilEntity> findAll(Pageable pageable);

    public Optional<CounterfoilEntity> findById(Long id);

    public CounterfoilEntity save(CounterfoilEntity counterfoilEntity);

    public void deleteById(Long id);

    public List<CounterfoilEntity> findAllByUserIdAndCodeOrName(Long userId, String code,
                                                         String name);

    public CounterfoilEntity activate(Long id, Long userId);

    public CounterfoilEntity deactivate(Long id, Long userId);

}
