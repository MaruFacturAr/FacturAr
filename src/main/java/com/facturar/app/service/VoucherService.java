package com.facturar.app.service;

import com.facturar.app.entity.ItemEntity;
import com.facturar.app.entity.VoucherEntity;

import java.util.List;
import java.util.Optional;

public interface VoucherService {

    public Iterable<VoucherEntity> findAll();

    public Optional<VoucherEntity> findById(Long id);

    public VoucherEntity save(VoucherEntity voucher);

    public void deleteById(Long id);

    public List<VoucherEntity> findAllByUserIdOrName(Long userId,
                                                         String name);
    public VoucherEntity activate(Long id, Long userId);

    public VoucherEntity deactivate(Long id, Long userId);
}
