package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.dto.Question.QuestionRequestDTO;
import org.mrstm.quoraapi.exceptions.NotFoundException;
import org.mrstm.quoraapi.models.Question;
import org.mrstm.quoraapi.models.Topic;
import org.mrstm.quoraapi.models.User;
import org.mrstm.quoraapi.repositories.QuestionRepository;
import org.mrstm.quoraapi.repositories.TopicRepository;
import org.mrstm.quoraapi.repositories.UserRepository;
import org.mrstm.quoraapi.utils.searchSpecifications.QuestionSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        //obtaining existing topics nd storing them in set
        List<Topic> existingTopics = topicRepository.findAllByNameIn(topicsByUser);
        Set<String> existingTopicsSet = existingTopics.stream()
                .map(Topic::getName)
                .collect(Collectors.toSet());

        //filter existing topics nd which are not present add them in newTopics.
        List<Topic> newTopics = topicsByUser.stream()
                .filter(name -> !existingTopicsSet.contains(name))
                .map(name -> {
                    Topic newTopic = new Topic();
                    newTopic.setName(name);
                    return newTopic;
                })
                .toList();

        topicRepository.saveAll(newTopics);

        //prepare a list of existing and new topics.
        List<Topic> allTopics = new ArrayList<>();
        allTopics.addAll(existingTopics);
        allTopics.addAll(newTopics);


        //prepare new question
        Question newQuestion = Question.builder()
                .title(question.getTitle())
                .body(question.getBody())
                .user(user)
                .topics(allTopics)
                .build();
        return questionRepository.save(newQuestion);
    }

    public List<Question> searchQuestions(String title , List<String> topics){
        Specification<Question> spec = Specification
                .where(QuestionSpecification.questionSpecification(title))
                .and(QuestionSpecification.hasTopicIn(topics));

        return questionRepository.findAll(spec);
    }

    public Question getQuestion(@PathVariable int id) {
        return questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Question not found"));
    }
}
