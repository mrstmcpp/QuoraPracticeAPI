package org.mrstm.quoraapi.controllers;


import org.mrstm.quoraapi.dto.Answer.AnswerAddRequestDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerResponseDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerUpdateRequestDTO;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswerResponseDTO> getAnswers(@PathVariable int questionId , @RequestBody AnswerAddRequestDTO answerDto) {
        AnswerResponseDTO answer = answerService.addAnswer(questionId, answerDto);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @PutMapping("/answers/{answerId}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable int answerId, @RequestBody AnswerUpdateRequestDTO answerDto) {
        Answer answer = answerService.updateAnswer(answerId , answerDto);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
