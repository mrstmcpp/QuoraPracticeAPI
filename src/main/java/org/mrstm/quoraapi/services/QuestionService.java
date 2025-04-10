package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.dto.QuestionRequestDTO;
import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.QuestionRepository;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Question addQuestion(QuestionRequestDTO question){
        int userId = question.getUserid();
        System.out.println(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));


        Question newQuestion = new Question();
        newQuestion.setTitle(question.getTitle());
        newQuestion.setBody(question.getBody());
        newQuestion.setUser(user);
        return questionRepository.save(newQuestion);
    }


}
