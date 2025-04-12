package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.dto.Answer.AnswerAddDTO;
import org.mrstm.quoraapi.exceptions.CannotBeEmptyException;
import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.AnswerRepository;
import org.mrstm.quoraapi.repositories.QuestionRepository;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Answer addAnswer(int questionId, AnswerAddDTO answerDto) {
        Question q = questionRepository.findById(questionId).get();
        if(q == null) {
            throw new NotFoundException("Question not found");
        }
        if(answerDto.getText() == null || answerDto.getText().isEmpty()) {
            throw new CannotBeEmptyException("Text cannot be empty");
        }

        User u = userRepository.findById(answerDto.getUserid()).get();
        return answerRepository.save(Answer.builder()
                .text(answerDto.getText())
                .user(u)
                .question(q)
                .build());
    }


}
