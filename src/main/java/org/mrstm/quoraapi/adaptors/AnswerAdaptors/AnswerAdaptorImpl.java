package org.mrstm.quoraapi.adaptors.AnswerAdaptors;

import org.mrstm.quoraapi.dto.Answer.AnswerAddRequestDTO;
import org.mrstm.quoraapi.dto.Answer.AnswerResponseDTO;
import org.mrstm.quoraapi.exceptions.CannotBeEmptyException;
import org.mrstm.quoraapi.exceptions.NotFoundException;
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

    public Answer fromDto(int questionId,  AnswerAddRequestDTO answerAddRequestDTO) {
        if(!userRepository.existsById(answerAddRequestDTO.getUserid())) {
            throw new NotFoundException("User with id " + answerAddRequestDTO.getUserid() + " does not exist.");
        }
        if(answerAddRequestDTO.getText() == null || answerAddRequestDTO.getText().isEmpty()) {
            throw new CannotBeEmptyException("Text cannot be empty.");
        }

        User user = userRepository.findById(answerAddRequestDTO.getUserid())
                .orElseThrow(() -> new NotFoundException("User with id " + answerAddRequestDTO.getUserid() + " does not exist."));

        Question q = questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Question with id " + questionId + " does not exist."));

        return Answer.builder()
                .text(answerAddRequestDTO.getText())
                .question(q)
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
