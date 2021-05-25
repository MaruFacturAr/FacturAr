package com.facturar.app.controller;

import com.facturar.app.config.JwtTokenUtil;
import com.facturar.app.entity.JwtRequest;
import com.facturar.app.entity.JwtResponse;
import com.facturar.app.entity.MessageResponse;
import com.facturar.app.entity.UserEntity;
import com.facturar.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserEntity userEntity = userService.findByuserName(authenticationRequest.getUsername());

        if (userEntity == null) {
            return new ResponseEntity(new MessageResponse("User not found with username: " + authenticationRequest.getUsername()), HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = new User(userEntity.getUserName(), userEntity.getPassword(),
                new ArrayList<>());

        /*final UserDetails userDetails = jwtInMemoryUserDetailsService
              .loadUserByUsername(authenticationRequest.getUsername());*/

        final String token = jwtTokenUtil.generateToken(userDetails, userEntity.getId());

        return ResponseEntity.ok(new JwtResponse(token, jwtTokenUtil.JWT_TOKEN_VALIDITY));
    }

    // Create a new user

    @RequestMapping(value = "/api/registerUser", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody UserEntity user) throws NoSuchAlgorithmException {
        UserEntity userEntity = userService.findByuserName(user.getUserName());
        if(userEntity != null)
            return new ResponseEntity(new MessageResponse("Ya existe ese nombre de usuario"), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
