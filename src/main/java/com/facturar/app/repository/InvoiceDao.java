package com.facturar.app.repository;

import com.facturar.app.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDao extends JpaRepository<InvoiceEntity, Long> {

    @Query("SELECT c FROM InvoiceEntity c WHERE (c.userId = :userId  and (:name is null"
            + " or c.number = :name))")
    List<InvoiceEntity> findAllByUserIdOrName(@Param("userId") Long userId,
                                              @Param("name") String name);

    Optional<InvoiceEntity> findByIdAndUserId(Long id, Long userId);

    List<InvoiceEntity> findByUserId(Long userId);
}
