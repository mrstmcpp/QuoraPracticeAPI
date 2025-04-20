package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.dto.User.UserUpdateDTO;
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

    public User updateUser(int userId , UserUpdateDTO userDto){
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        if (userDto.getUsername() != null &&
                !userDto.getUsername().equals(existingUser.getUsername()) &&
                userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new AlreadyExistsException("Username is already in use");
        }

        if (userDto.getEmail() != null &&
                !userDto.getEmail().equals(existingUser.getEmail()) &&
                userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new AlreadyExistsException("Email is already in use");
        }

        if (userDto.getUsername() != null) existingUser.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null) existingUser.setEmail(userDto.getEmail());
        if (userDto.getBio() != null) existingUser.setBio(userDto.getBio());
        if(userDto.getPassword() != null && !userDto.getPassword().isBlank()) existingUser.setPassword(userDto.getPassword());
        return userRepository.save(existingUser);

    }


    public Boolean followUser(int userId, int targetUserId){
        if (userId == targetUserId) {
            throw new IllegalArgumentException("User cannot follow themselves");
        }
        User currUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Current User not found"));
        User targetUser = userRepository.findById(targetUserId).orElseThrow(() -> new NotFoundException("Target User not found"));

        if(currUser.getFollowing().contains(targetUser)){
            return false;
        }
        currUser.getFollowing().add(targetUser);
        userRepository.save(currUser);
        return true;
    }
}
