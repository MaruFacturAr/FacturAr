package com.facturar.app.service;

import com.facturar.app.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DocumentService {
    public Iterable<Document> findAll();

    public Page<Document> findAll(Pageable pageable);

    public Optional<Document> findById(Long id);

    public Document save(Document document);

    public void deleteById(Long id);
}
