package org.mrstm.quoraapi.controllers;

import org.mrstm.quoraapi.models.Topic;
import org.mrstm.quoraapi.services.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/topics")
public class TopicController {
    private TopicService topicService;
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/")
    public Topic addTopic(@RequestBody Topic topic){
        return topicService.addTopic(topic);
    }

    @GetMapping("/")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }
}
