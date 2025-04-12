package org.mrstm.quoraapi.dto.User;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private int id;
    private String email;
    private String username;
    private String bio;
    @JsonIgnore
    private String password;
}
