package com.facturar.app.repository;

import com.facturar.app.entity.TaxpayerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxpayerTypeDao extends JpaRepository<TaxpayerType, Long> {
}
