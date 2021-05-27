package com.facturar.app.service;

import com.facturar.app.entity.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentTypeService {

    public Iterable<DocumentType> findAll();

    public Page<DocumentType> findAll(Pageable pageable);
}
