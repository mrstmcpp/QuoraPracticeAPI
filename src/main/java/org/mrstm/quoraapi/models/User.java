package org.mrstm.quoraapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


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

    @Column(unique = true)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Column
    @NotBlank(message = "Password cannot be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //to avoid getting fetch through api
    private String password;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email cannot be blank.")
    @Column(unique = true)
    private String email;

    private String bio;

    // This user is the FOLLOWER (me), I'm following others
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "follower_id"),         // ME
            inverseJoinColumns = @JoinColumn(name = "followee_id")   // THEM

    )
    private Set<User> following = new HashSet<>(); // who I follow

    // USERS who follow ME
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>(); // who follows me

}
