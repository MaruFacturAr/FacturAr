package com.facturar.app.repository;

import com.facturar.app.entity.BillingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingDataDao extends JpaRepository<BillingData, Long> {
}
