package com.facturar.app.repository;

import com.facturar.app.entity.SalesPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesPointDao extends JpaRepository<SalesPointEntity, Long> {

    @Query("SELECT c FROM SalesPointEntity c WHERE (c.userId = :userId and (:code is null or c.afip_code = :code) and (:name is null"
            + " or c.name = :name))")
    List<SalesPointEntity> findAllByUserIdAndCodeOrName(@Param("userId") Long userId, @Param("code")String code,
                                                  @Param("name") String name);


    Optional<SalesPointEntity> findByIdAndUserId(Long id, Long userId);
}
