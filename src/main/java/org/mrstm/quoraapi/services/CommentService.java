package org.mrstm.quoraapi.services;

import jakarta.transaction.Transactional;
import org.mrstm.quoraapi.adaptors.CommentAdaptors.CommentAdaptors;
import org.mrstm.quoraapi.dto.Comment.CommentRequestDTO;
import org.mrstm.quoraapi.dto.Comment.CommentResponseDTO;
import org.mrstm.quoraapi.exceptions.CannotBeEmptyException;
import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.models.Comment;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.AnswerRepository;
import org.mrstm.quoraapi.repositories.CommentRepository;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final CommentAdaptors commentAdaptors;
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, AnswerRepository answerRepository, CommentAdaptors commentAdaptors) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.commentAdaptors = commentAdaptors;
    }


    @Transactional
    public CommentResponseDTO addComment(int answerId , CommentRequestDTO commentRequestDTO) {
        if(commentRequestDTO.getText() == null || commentRequestDTO.getText().isEmpty())
            throw new CannotBeEmptyException("Text cannot be empty");
        User user = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new NotFoundException("Answer not found"));
        Comment comment = commentAdaptors.fromDto(commentRequestDTO, user, answer);
        commentRepository.save(comment);
        return commentAdaptors.toDto(comment);
    }


    @Transactional
    public CommentResponseDTO addCommentOnComment(int commentId , CommentRequestDTO commentRequestDTO) {
        if(commentRequestDTO.getText() == null || commentRequestDTO.getText().isEmpty())
            throw new CannotBeEmptyException("Text cannot be empty");

        Comment parentComment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));
        User user = userRepository.findById(commentRequestDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Comment c = commentAdaptors.fromCommentDto(commentRequestDTO , parentComment, user);
        parentComment.addReplies(c);
        commentRepository.save(c);
        return commentAdaptors.toDto(c);
    }


}
