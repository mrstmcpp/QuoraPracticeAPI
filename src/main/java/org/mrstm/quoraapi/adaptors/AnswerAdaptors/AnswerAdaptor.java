package org.mrstm.quoraapi.adaptors.AnswerAdaptors;


import org.mrstm.quoraapi.dto.Answer.AnswerAddRequestDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerResponseDTO;
import org.mrstm.quoraapi.models.Answer;
import org.springframework.stereotype.Component;

@Component
public interface AnswerAdaptor {
    Answer fromDto(int questionId, AnswerAddRequestDTO answerAddRequestDTO);

    AnswerResponseDTO toDto(int questionId, Answer answer);
}
