package com.facturar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturar.app.entity.ItemEntity;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, Long> {

}
