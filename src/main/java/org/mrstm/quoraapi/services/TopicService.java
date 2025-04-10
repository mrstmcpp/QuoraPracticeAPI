package org.mrstm.quoraapi.services;

import org.mrstm.quoraapi.exceptions.AlreadyExistsException;
import org.mrstm.quoraapi.models.Topic;
import org.mrstm.quoraapi.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic addTopic(Topic topic) {
        if(topicRepository.existsByName(topic.getName())){
            throw new AlreadyExistsException("Topic already exists");
        }
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }
}
