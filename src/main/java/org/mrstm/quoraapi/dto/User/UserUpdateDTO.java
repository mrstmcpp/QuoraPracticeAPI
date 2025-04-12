package org.mrstm.quoraapi.dto.User;


import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;
    private String email;
    private String password;
    private String bio;
}
