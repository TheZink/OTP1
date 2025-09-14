package com.attendace.Controller;

import com.attendace.Model.TopicModel;

public class TopicController {
    private TopicModel topicModel;


    //add dao later
    public TopicController() {

    }
    public void addTopic(String topicName) {
        topicModel = new TopicModel(topicName);
        System.out.println("Created a new topic");
    }
    public TopicModel getTopic() {
        return topicModel;
    }
}
