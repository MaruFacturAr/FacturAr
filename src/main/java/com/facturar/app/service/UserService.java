package com.facturar.app.service;

import com.facturar.app.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService {

    public Iterable<UserEntity> findAll();

    public Page<UserEntity> findAll(Pageable pageable);

    public Optional<UserEntity> findById(Long id);

    public UserEntity save(UserEntity user) throws NoSuchAlgorithmException;

    public void deleteById(Long id);

    public UserEntity findByuserName(String userName);

}