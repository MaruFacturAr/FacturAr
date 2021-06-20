package com.facturar.app.service;

import com.facturar.app.entity.VoucherEntity;
import com.facturar.app.repository.VoucherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService{

    @Autowired
    private VoucherDao voucherDao;

    @Override
    public Iterable<VoucherEntity> findAll() {
        return voucherDao.findAll();
    }

    @Override
    public Optional<VoucherEntity> findById(Long id) {
        return voucherDao.findById(id);
    }

    @Override
    public VoucherEntity save(VoucherEntity voucher) {
        return voucherDao.save(voucher);
    }

    @Override
    public void deleteById(Long id) {
        voucherDao.deleteById(id);
    }

    @Override
    public List<VoucherEntity> findAllByUserIdOrName(Long userId, String name) {
        return voucherDao.findAllByUserIdOrName(userId,name);
    }

    @Override
    public VoucherEntity activate(Long id, Long userId) {
        Optional<VoucherEntity> voucherEntity = voucherDao.findByIdAndUserId(id, userId);
        voucherEntity.get().setStatus_id(1);
        return  voucherDao.save(voucherEntity.get());
    }

    @Override
    public VoucherEntity deactivate(Long id, Long userId) {
        Optional<VoucherEntity> voucherEntity = voucherDao.findByIdAndUserId(id, userId);
        voucherEntity.get().setStatus_id(2);
        return  voucherDao.save(voucherEntity.get());
    }
}
