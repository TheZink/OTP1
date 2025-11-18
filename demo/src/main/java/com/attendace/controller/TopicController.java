package com.attendace.controller;

import com.attendace.model.TopicModel;

public class TopicController {
    private TopicModel topicModel;

    public void addTopic(String topicName) {
        topicModel = new TopicModel(topicName);
    }
    public TopicModel getTopic() {
        return topicModel;
    }
}
