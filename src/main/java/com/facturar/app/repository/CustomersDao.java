package com.facturar.app.repository;

import com.facturar.app.entity.CounterfoilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facturar.app.entity.CustomersEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersDao extends JpaRepository<CustomersEntity, Long> {

    @Query("SELECT c FROM CustomersEntity c JOIN c.billing_data_id b WHERE (c.userId = :userId and (:id is null or c.id = :id) and (:legal_name is null"
            + " or b.legal_name = :legal_name))")
    List<CustomersEntity> findAllByUserIdAndCodeOrName(@Param("userId") Long userId, @Param("id")String code,
                                                         @Param("legal_name") String name);


    Optional<CustomersEntity> findByIdAndUserId(Long id, Long userId);

    Iterable<CustomersEntity> findByUserId(Long userId);
	
}
