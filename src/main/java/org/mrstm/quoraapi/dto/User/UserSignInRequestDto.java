package org.mrstm.quoraapi.dto.User;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequestDto {
    private String username;
    private String password;
}
