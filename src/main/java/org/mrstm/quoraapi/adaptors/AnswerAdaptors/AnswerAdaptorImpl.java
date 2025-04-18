package org.mrstm.quoraapi.adaptors.AnswerAdaptors;

import org.mrstm.quoraapi.dto.Answer.AnswerResponseDTO;
import org.mrstm.quoraapi.models.Answer;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.QuestionRepository;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class AnswerAdaptorImpl implements AnswerAdaptor {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public AnswerAdaptorImpl(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Answer fromDto(String text, User user, Question question) {
        return Answer.builder()
                .text(text)
                .question(question)
                .user(user)
                .build();
    }

    @Override
    public AnswerResponseDTO toDto(int questionId, Answer answer) {
        return AnswerResponseDTO.builder()
                .text(answer.getText())
                .answeredBy(answer.getUser().getUsername())
                .questionId(questionId)
                .build();
    }
}
