package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.exceptions.AlreadyExistsException;
import org.mrstm.quoraapi.exceptions.NotFoundException;
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
            throw new AlreadyExistsException("Username is already in use");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsException("Email is already in use");
        }
        userRepository.save(user);
        return user;
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found"));

        if(existingUser.getUsername().equals(user.getUsername()) && userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new AlreadyExistsException("Username is already in use");
        }

        if(existingUser.getEmail().equals(user.getEmail()) && userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new AlreadyExistsException("Email is already in use");
        }
//        UserResponseDTO userResponseDTO = new UserResponseDTO();
        existingUser.setBio(user.getBio());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);

        return existingUser;

    }
}
