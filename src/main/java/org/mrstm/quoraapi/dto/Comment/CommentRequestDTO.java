package org.mrstm.quoraapi.dto.Comment;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private int userId;
    private String text;
}
