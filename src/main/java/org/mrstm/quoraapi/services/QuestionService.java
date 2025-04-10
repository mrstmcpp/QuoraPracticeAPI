package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.dto.QuestionRequestDTO;
import org.mrstm.quoraapi.dto.QuestionResponseDTO;
import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.Topic;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.QuestionRepository;
import org.mrstm.quoraapi.repositories.TopicRepository;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public Question addQuestion(QuestionRequestDTO question){
        int userId = question.getUserid();
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<String> topicsByUser = question.getTopics();
        List<Topic> topics = topicRepository.findAllByNameIn(topicsByUser);

        /* todo -> if topic isn't present in the db then add new topic nd send it; */

        Question newQuestion = new Question();
        newQuestion.setTitle(question.getTitle());
        newQuestion.setBody(question.getBody());
        newQuestion.setUser(user);
        newQuestion.setTopics(topics);
        return questionRepository.save(newQuestion);
    }

    public List<QuestionResponseDTO> getAllQuestions() { // temporary api for testing
        List<Question> questionList = questionRepository.findAll();
        List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<>();

        for (Question question : questionList) {
            QuestionResponseDTO dto = new QuestionResponseDTO();
            dto.setTitle(question.getTitle());
            dto.setBody(question.getBody());
            List<Topic> topicsByUser = question.getTopics();
            dto.setUsername(question.getUser().getUsername());
            List<String> topics = new ArrayList<>();
            for (Topic topic : topicsByUser) {
                topics.add(topic.getName());
            }
            dto.setTopics(topics);
            questionResponseDTOList.add(dto);
        }
        return questionResponseDTOList;
    }

}
