package org.mrstm.quoraapi.adaptors.CommentAdaptors;


import org.mrstm.quoraapi.dto.Comment.CommentRequestDTO;
import org.mrstm.quoraapi.dto.Comment.CommentResponseDTO;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.models.Comment;
import org.mrstm.quoraapi.models.User;
import org.springframework.stereotype.Component;

@Component
public interface CommentAdaptors {
    Comment fromDto(CommentRequestDTO dto, User user, Answer answer);
    CommentResponseDTO toDto(Comment comment);

    Comment fromCommentDto(CommentRequestDTO dto, Comment parentComment , User user);
}
