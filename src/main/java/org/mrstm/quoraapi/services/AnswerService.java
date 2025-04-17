package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.adaptors.AnswerAdaptors.AnswerAdaptor;
import org.mrstm.quoraapi.adaptors.AnswerAdaptors.AnswerAdaptorImpl;
import org.mrstm.quoraapi.dto.Answer.AnswerAddRequestDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerResponseDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerUpdateRequestDTO;
import org.mrstm.quoraapi.exceptions.CannotBeEmptyException;
import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerAdaptorImpl answerAdaptor;

    public AnswerService(AnswerRepository answerRepository, AnswerAdaptorImpl answerAdaptor) {
        this.answerRepository = answerRepository;
        this.answerAdaptor = answerAdaptor;
    }

    public AnswerResponseDTO addAnswer(int questionId, AnswerAddRequestDTO answerDto) {
        Answer ans = answerAdaptor.fromDto(questionId, answerDto);
        answerRepository.save(ans);
        return answerAdaptor.toDto(questionId, ans);
    }

    public Answer updateAnswer(int answerId, AnswerUpdateRequestDTO answerDto) {
        Answer answer = answerRepository.findById(answerId).get();
        if(answer == null) {
            throw new NotFoundException("Answer not found");
        }
        if(answerDto.getText() == null || answerDto.getText().isEmpty()) {
            throw new CannotBeEmptyException("Text cannot be empty");
        }
        answer.setText(answerDto.getText());
        return answerRepository.save(answer);
    }

}
