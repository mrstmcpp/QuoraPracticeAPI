package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Like;
import org.mrstm.quoraapi.models.LikeEntity;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.*;
import org.springframework.stereotype.Service;


@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository, CommentRepository commentRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.commentRepository = commentRepository;
    }

    public Boolean addLike(String entityType , int entityId ,int userId){
        //verifying data
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
        if(entityType.equals("answers") && !answerRepository.existsById(Math.toIntExact(entityId))){
            return false;
        }else if(entityType.equals("questions") && !questionRepository.existsById(entityId)){
            return false;
        }else if(entityType.equals("comments") && !commentRepository.existsById(entityId)){
            return false;
        }
//        Boolean check = likeRepository.existsByUserIdAndEntityTypeAndEntityId(entityType , entityId , userId);
//        if(check){
//            return false;
//        }

        Like like = Like.builder()
                .type(LikeEntity.valueOf(entityType))
                .entityId(entityId)
                .user(user)
                .build();
        likeRepository.save(like);
        return true;
    }
}
