package com.facturar.app.service;

import com.facturar.app.entity.UserEntity;
import com.facturar.app.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    private final UserDao userDaoRepository;

    @Autowired
    public JwtUserDetailsService(UserDao userDaoRepo){
        userDaoRepository = userDaoRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userDaoRepository.findByuserName(userName);
        if (userEntity != null) {
            return new User(userEntity.getUserName(), userEntity.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
    }

}