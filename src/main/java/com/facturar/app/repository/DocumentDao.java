package com.facturar.app.repository;

import com.facturar.app.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDao  extends JpaRepository<Document, Long> {
}
