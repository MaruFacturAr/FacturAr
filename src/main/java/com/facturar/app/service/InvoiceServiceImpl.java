package com.facturar.app.service;

import com.facturar.app.entity.InvoiceEntity;
import com.facturar.app.repository.InvoiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<InvoiceEntity> findAll() {
        return invoiceDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceEntity> findById(Long id) {
        return invoiceDao.findById(id);
    }

    @Override
    @Transactional
    public InvoiceEntity save(InvoiceEntity invoice) {
        return invoiceDao.save(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceEntity> findByIdAndUserId(Long id, Long userId) {
        return invoiceDao.findByIdAndUserId(id, userId);
    }

    @Override
    public List<InvoiceEntity> findByUserId(Long userId) {
        return invoiceDao.findByUserId(userId);
    }

    @Override
    public List<InvoiceEntity> findAllByUserIdOrName(Long userId, String name) {
        return invoiceDao.findAllByUserIdOrName(userId, name);
    }
}
