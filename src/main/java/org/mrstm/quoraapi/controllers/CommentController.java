package org.mrstm.quoraapi.controllers;


import jakarta.validation.Valid;
import org.mrstm.quoraapi.dto.Comment.CommentRequestDTO;
import org.mrstm.quoraapi.dto.Comment.CommentResponseDTO;
import org.mrstm.quoraapi.models.Comment;
import org.mrstm.quoraapi.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/answers/{answerId}/comments")
    public ResponseEntity<CommentResponseDTO> commentOnAnswer(@PathVariable int answerId , @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO comment = commentService.addComment(answerId, commentRequestDTO);
        return new ResponseEntity<>(comment , HttpStatus.CREATED);
    }


    @PostMapping("/comments/{commentId}/comments")
    public ResponseEntity<CommentResponseDTO> commentOnComment(@PathVariable int commentId , @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO comment = commentService.addCommentOnComment(commentId, commentRequestDTO);
        return new ResponseEntity<>(comment , HttpStatus.CREATED);
    }

}
