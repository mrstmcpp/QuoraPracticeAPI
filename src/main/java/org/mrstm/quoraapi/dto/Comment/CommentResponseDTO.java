package org.mrstm.quoraapi.dto.Comment;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private int userId;
    private String text;
}
