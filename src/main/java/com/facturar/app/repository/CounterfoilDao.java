package com.facturar.app.repository;

import com.facturar.app.entity.CounterfoilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounterfoilDao extends JpaRepository<CounterfoilEntity, Long> {
    @Query("SELECT c FROM CounterfoilEntity c WHERE (c.userId = :userId and (:code is null or c.code = :code) and (:name is null"
            + " or c.name = :name))")
    List<CounterfoilEntity> findAllByUserIdAndCodeOrName(@Param("userId") Long userId, @Param("code")String code,
                                                  @Param("name") String name);


    Optional<CounterfoilEntity> findByIdAndUserId(Long id, Long userId);

    Iterable<CounterfoilEntity> findByUserId(Long userId);
}
