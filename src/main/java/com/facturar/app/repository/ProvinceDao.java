package com.facturar.app.repository;

import com.facturar.app.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceDao extends JpaRepository<Province, Long> {
}
