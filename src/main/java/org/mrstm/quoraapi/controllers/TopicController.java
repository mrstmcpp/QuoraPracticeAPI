package org.mrstm.quoraapi.controllers;

import jakarta.validation.Valid;
import org.mrstm.quoraapi.models.Topic;
import org.mrstm.quoraapi.services.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Topic> addTopic(@RequestBody @Valid Topic topic){
        Topic createedTopic = topicService.addTopic(topic);
        return new ResponseEntity<>(createedTopic, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }
}
