package com.facturar.app.service;

import com.facturar.app.entity.CounterfoilEntity;
import com.facturar.app.repository.CounterfoilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CounterfoilServiceImpl implements CounterfoilService{

    @Autowired
    private CounterfoilDao counterfoilDao;

    @Override
    public Iterable<CounterfoilEntity> findAll() {
        return counterfoilDao.findAll();
    }

    @Override
    public Page<CounterfoilEntity> findAll(Pageable pageable) {
        return counterfoilDao.findAll(pageable);
    }

    @Override
    public Optional<CounterfoilEntity> findById(Long id) {
        return counterfoilDao.findById(id);
    }

    @Override
    public CounterfoilEntity save(CounterfoilEntity counterfoilEntity) {
        return counterfoilDao.save(counterfoilEntity);
    }

    @Override
    public void deleteById(Long id) {
        counterfoilDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CounterfoilEntity> findAllByUserIdAndCodeOrName(Long userId, String code, String name) {
        return counterfoilDao.findAllByUserIdAndCodeOrName(userId, code, name);
    }

    @Override
    public CounterfoilEntity activate(Long id, Long userId) {
        Optional<CounterfoilEntity> counterfoilEntity = counterfoilDao.findByIdAndUserId(id, userId);
        counterfoilEntity.get().setStatus_id(1);
        return counterfoilEntity.get();
    }

    @Override
    public CounterfoilEntity deactivate(Long id, Long userId) {
        Optional<CounterfoilEntity> counterfoilEntity = counterfoilDao.findByIdAndUserId(id, userId);
        counterfoilEntity.get().setStatus_id(2);
        return counterfoilEntity.get();
    }
}
