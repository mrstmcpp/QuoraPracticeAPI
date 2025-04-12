package org.mrstm.quoraapi.controllers;


import org.mrstm.quoraapi.dto.Answer.AnswerAddDTO;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<Answer> getAnswers(@PathVariable int questionId , @RequestBody AnswerAddDTO answerDto) {
        Answer answer = answerService.addAnswer(questionId, answerDto);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }


}
