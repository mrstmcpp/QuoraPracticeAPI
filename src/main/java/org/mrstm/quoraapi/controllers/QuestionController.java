package org.mrstm.quoraapi.controllers;


import jakarta.validation.Valid;

import org.mrstm.quoraapi.dto.QuestionRequestDTO;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody @Valid QuestionRequestDTO questionRequestDTO ) {
        Question createdQuestion = questionService.addQuestion(questionRequestDTO);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

}
