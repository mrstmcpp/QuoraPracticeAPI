package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(User user){
        if(user.getEmail() == null || user.getEmail().isEmpty() || user.getUsername() == null || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }
        userRepository.save(user);
        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
