package com.facturar.app.service;

import com.facturar.app.entity.SalesPointEntity;
import com.facturar.app.repository.SalesPointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalesPointServiceImpl implements SalesPointService{

    @Autowired
    private SalesPointDao salesPointDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<SalesPointEntity> findAll() {
        return salesPointDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesPointEntity> findById(Long id) {
        return salesPointDao.findById(id);
    }

    @Override
    public SalesPointEntity save(SalesPointEntity salesPoint) {
        return salesPointDao.save(salesPoint);
    }

    @Override
    public void deleteById(Long id) {
        salesPointDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesPointEntity> findAllByUserIdAndCodeOrName(Long userId, String code, String name) {
        return salesPointDao.findAllByUserIdAndCodeOrName(userId, code, name);
    }

    @Override
    public SalesPointEntity activate(Long id, Long userId) {
        Optional<SalesPointEntity> salesPoint = salesPointDao.findByIdAndUserId(id, userId);
        salesPoint.get().setStatus_id(1);
        return  salesPointDao.save(salesPoint.get());
    }

    @Override
    public SalesPointEntity deactivate(Long id, Long userId) {
        Optional<SalesPointEntity> salesPoint = salesPointDao.findByIdAndUserId(id, userId);
        salesPoint.get().setStatus_id(2);
        return  salesPointDao.save(salesPoint.get());
    }

    @Override
    public Iterable<SalesPointEntity> findByUserId(Long userId) {
        return salesPointDao.findByUserId(userId);
    }
}
