package com.facturar.app.service;

import com.facturar.app.entity.UserEntity;
import com.facturar.app.repository.UserDao;
import com.facturar.app.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<UserEntity> findAll() {
        return userDaoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> findAll(Pageable pageable) {
        return userDaoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return userDaoRepository.findById(id);
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity user) throws NoSuchAlgorithmException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        byte[]  salt = com.facturar.app.utils.Encrypt.getSalt();
        String password = passwordEncoder.encode(user.getPassword());//Encrypt.getSecurePassword(user.getPassword(), salt);
        user.setPassword(password);
        user.setSalt(new String(salt, StandardCharsets.UTF_8));

        return userDaoRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDaoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByuserName(String userName) {
        UserEntity user = null;
        try
        {
            user = userDaoRepository.findByuserName(userName);

        }catch(Exception ex){

        }
        return user;
    }

}
