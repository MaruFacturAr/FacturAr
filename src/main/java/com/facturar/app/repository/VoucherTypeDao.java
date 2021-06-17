package com.facturar.app.repository;

import com.facturar.app.entity.VoucherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherTypeDao extends JpaRepository<VoucherType, Long> {
}
