package org.mrstm.quoraapi.controllers;


import org.mrstm.quoraapi.dto.User.UserUpdateDTO;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> signup(@RequestBody User user){
        User newUser = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .bio(user.getBio())
                .build();
        User createdUser = userService.signup(newUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId){
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody UserUpdateDTO userDto) {

        User updatedUser = userService.updateUser(userId ,userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
