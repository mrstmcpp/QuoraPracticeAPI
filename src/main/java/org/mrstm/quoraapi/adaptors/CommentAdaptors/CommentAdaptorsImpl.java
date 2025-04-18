package org.mrstm.quoraapi.adaptors.CommentAdaptors;

import org.mrstm.quoraapi.dto.Comment.CommentRequestDTO;
import org.mrstm.quoraapi.dto.Comment.CommentResponseDTO;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.models.Comment;
import org.mrstm.quoraapi.models.User;
import org.springframework.stereotype.Component;

@Component
public class CommentAdaptorsImpl implements CommentAdaptors {

    @Override
    public Comment fromDto(CommentRequestDTO dto, User user, Answer answer) {
        return Comment.builder()
                .text(dto.getText())
                .user(user)
                .answer(answer)
                .build();
    }

    @Override
    public CommentResponseDTO toDto(Comment comment) {
        return CommentResponseDTO.builder()
                .text(comment.getText())
                .userId(comment.getUser().getId())
                .build();
    }

    @Override
    public Comment fromCommentDto(CommentRequestDTO dto, Comment parentComment , User user) {
        return Comment.builder()
                .text(dto.getText())
                .user(user)
                .answer(parentComment.getAnswer())
                .parent(parentComment)
                .build();
    }
}
