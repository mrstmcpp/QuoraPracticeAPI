package org.mrstm.quoraapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Username cannot be empty")
    @Column(unique = true , nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email(message = "Please provide a valid email address")
    @Column(unique = true , nullable = false)
    private String email;

    private String bio;

}
