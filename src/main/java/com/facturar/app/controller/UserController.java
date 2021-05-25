package com.facturar.app.controller;

import com.facturar.app.entity.UserEntity;
import com.facturar.app.entity.UserSimpleInfoResponse;
import com.facturar.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Read user

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) {

        Optional<UserEntity> user = userService.findById(userId);

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);

    }


    @GetMapping("/userSimpleInfo/{userName}")
    public ResponseEntity<?> userSimpleInfo(@PathVariable(value = "userName") String userName) {

        UserEntity user = userService.findByuserName(userName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserSimpleInfoResponse(user.getId(), user.getUserName(),
                user.getName(), user.getLast_name(), user.getEmail()));

    }

    @GetMapping("/userFullInfo/{userName}")
    public ResponseEntity<?> userFullInfo(@PathVariable(value = "userName") String userName) {

        UserEntity user = userService.findByuserName(userName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);

    }

    // Update User

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserEntity userDetails, @PathVariable(value = "id") Long userId) throws NoSuchAlgorithmException {

        Optional<UserEntity> user = userService.findById(userId);

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // BeanUtils.copyProperties(userDetails, user.get());
        user.get().setName(userDetails.getName());
        user.get().setEmail(userDetails.getEmail());


        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
    }

    //Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {

        if (!userService.findById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(userId);

        return new ResponseEntity<>("Se ha eliminado el usuario con id: " + userId, HttpStatus.OK);
    }


    //Read all

    @GetMapping
    public List<UserEntity> readAll(){

        List<UserEntity> users = StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return users;

    }


}