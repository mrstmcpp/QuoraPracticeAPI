package org.mrstm.quoraapi.controllers;


import jakarta.validation.Valid;

import org.mrstm.quoraapi.dto.QuestionRequestDTO;
import org.mrstm.quoraapi.dto.QuestionResponseDTO;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.Topic;
import org.mrstm.quoraapi.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponseDTO>> searchQuestion(@RequestParam(required = false) String title , @RequestParam(required = false) List<String> topics) {
        List<Question> questions = questionService.searchQuestions(title, topics);
        List<QuestionResponseDTO> response = questions.stream().map(q -> {
            return QuestionResponseDTO.builder()
                    .title(q.getTitle())
                    .username(q.getUser().getUsername())
                    .body(q.getBody())
                    .topics(q.getTopics().stream().map(Topic::getName).toList())
                    .build();
        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
