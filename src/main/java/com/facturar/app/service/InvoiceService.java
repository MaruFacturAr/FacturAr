package com.facturar.app.service;

import com.facturar.app.entity.InvoiceEntity;
import com.facturar.app.entity.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    public Iterable<InvoiceEntity> findAll();

    public Optional<InvoiceEntity> findById(Long id);

    public InvoiceEntity save(InvoiceEntity invoice);

    public Optional<InvoiceEntity> findByIdAndUserId(Long id, Long userId);

    public List<InvoiceEntity> findByUserId(Long userId);
}
