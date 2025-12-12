package com.attendace.controller;

import com.attendace.model.TopicModel;

/**
 * Controller for managing topic-related operations.
 * Provides methods to add a topic and retrieve the current topic.
 */
public class TopicController {
    private TopicModel topicModel;
    private static final String TOPIC_NAME = "topicName";

    /**
     * Adds a new topic by creating a TopicModel instance.
     *
     * @param topicName the name of the topic to add
     */
    public void addTopic(String topicName) {
        topicModel = new TopicModel(topicName);
    }

    /**
     * Retrieves the current TopicModel.
     *
     * @return the current TopicModel instance
     */
    public TopicModel getTopic() {
        return topicModel;
    }
}