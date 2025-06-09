package org.mrstm.quoraapi.controllers;


import org.mrstm.quoraapi.dto.User.UserSignInRequestDto;
import org.mrstm.quoraapi.dto.User.UserSignInResponseDto;
import org.mrstm.quoraapi.dto.User.UserUpdateDTO;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.services.UserService;
import org.mrstm.quoraapi.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/")
    public ResponseEntity<User> signup(@RequestBody User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = User.builder()
                .email(user.getEmail())
                .password(encodedPassword)
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

    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<String> followUser(@PathVariable @Validated int userId, @PathVariable @Validated int targetUserId) {
        Boolean response = userService.followUser(userId, targetUserId);
        return new ResponseEntity<>(response ? "Success" : "Error", response ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public UserSignInResponseDto authenticateUser(@RequestBody UserSignInRequestDto userSignInRequestDto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSignInRequestDto.getUsername(), userSignInRequestDto.getPassword())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token =  jwtUtils.generateToken(userDetails.getUsername());
        return UserSignInResponseDto.builder()
                .userId(userDetails.getUsername())
                .token(token).build();

    }

}
