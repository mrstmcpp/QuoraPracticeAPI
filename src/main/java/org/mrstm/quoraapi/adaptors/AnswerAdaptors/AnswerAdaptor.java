package org.mrstm.quoraapi.adaptors.AnswerAdaptors;


import org.mrstm.quoraapi.dto.Answer.AnswerAddRequestDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerResponseDTO;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.User;
import org.springframework.stereotype.Component;

@Component
public interface AnswerAdaptor {
    Answer fromDto(String text, User user, Question question);

    AnswerResponseDTO toDto(int questionId, Answer answer);
}
