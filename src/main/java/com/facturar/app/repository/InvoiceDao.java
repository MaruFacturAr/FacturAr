package com.facturar.app.repository;

import com.facturar.app.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDao extends JpaRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findByIdAndUserId(Long id, Long userId);

    List<InvoiceEntity> findByUserId(Long userId);
}
