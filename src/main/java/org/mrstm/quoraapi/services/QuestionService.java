package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question addQuestion(Question question){
        if(question.getTitle() == null){
            throw new IllegalArgumentException("Question title cannot be null");
        }



        return questionRepository.save(question);
    }

}
