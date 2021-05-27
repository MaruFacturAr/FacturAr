package com.facturar.app.service;

import com.facturar.app.entity.DocumentType;
import com.facturar.app.repository.DocumentTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    @Autowired
    private DocumentTypeDao documentTypeDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<DocumentType> findAll() {
        return documentTypeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentType> findAll(Pageable pageable) {
        return documentTypeDao.findAll(pageable);
    }
}
