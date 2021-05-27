package com.facturar.app.repository;

import com.facturar.app.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeDao extends JpaRepository<DocumentType, Long> {
}
