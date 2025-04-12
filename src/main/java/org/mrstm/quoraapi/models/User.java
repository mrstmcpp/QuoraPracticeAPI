package org.mrstm.quoraapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(unique = true , nullable = false)
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //to avoid getting fetch through api
    private String password;

    @Email(message = "Please provide a valid email address")
    @Column(unique = true , nullable = false)
    private String email;

    private String bio;

}
