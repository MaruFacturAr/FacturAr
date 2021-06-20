package com.facturar.app.repository;

import com.facturar.app.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherDao extends JpaRepository<VoucherEntity, Long> {

    @Query("SELECT c FROM VoucherEntity c WHERE (c.userId = :userId  and (:name is null"
            + " or c.name = :name))")
    List<VoucherEntity> findAllByUserIdOrName(@Param("userId") Long userId,
                                                  @Param("name") String name);


    Optional<VoucherEntity> findByIdAndUserId(Long id, Long userId);
}
