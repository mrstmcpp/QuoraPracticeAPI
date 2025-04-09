package org.mrstm.quoraapi.repositories;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.mrstm.quoraapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(@NotBlank(message = "Username cannot be empty") String username);

    Optional<User> findByEmail(@Email(message = "Please provide a valid email address") String email);
}