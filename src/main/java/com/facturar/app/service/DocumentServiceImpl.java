package com.facturar.app.service;

import com.facturar.app.entity.Document;
import com.facturar.app.repository.DocumentDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService{

    private DocumentDao documentDao;
    @Override
    public Iterable<Document> findAll() {
        return documentDao.findAll();
    }

    @Override
    public Page<Document> findAll(Pageable pageable) {
        return documentDao.findAll(pageable);
    }

    @Override
    public Optional<Document> findById(Long id) {
        return documentDao.findById(id);
    }

    @Override
    public Document save(Document document) {
        return documentDao.save(document);
    }

    @Override
    public void deleteById(Long id) {
            documentDao.deleteById(id);
    }
}
