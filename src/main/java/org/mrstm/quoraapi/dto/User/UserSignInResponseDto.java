package org.mrstm.quoraapi.dto.User;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInResponseDto {
    private String userId;
    private String token;
}
