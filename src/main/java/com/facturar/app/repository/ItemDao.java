package com.facturar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.facturar.app.entity.ItemEntity;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT c FROM ItemEntity c WHERE (c.userId = :userId and (:code is null or c.code = :code) and (:name is null"
            + " or c.name = :name))")
    List<ItemEntity> findAllByUserIdAndCodeOrName(@Param("userId") Long userId, @Param("code")String code,
                                                  @Param("name") String name);

}
