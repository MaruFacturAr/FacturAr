package com.facturar.app.repository;

import com.facturar.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findByuserName(String userName);
}
