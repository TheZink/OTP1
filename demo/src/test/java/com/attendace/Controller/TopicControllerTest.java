package com.attendace.Controller;

import com.attendace.Model.TopicModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TopicControllerTest {
    private TopicController topicController;

    @BeforeEach
    void setup() {
        topicController = new TopicController();
    }

    @Test
    void addTopic() {
        topicController.addTopic("Engineering");
        TopicModel topic = topicController.getTopic();
        assertEquals("Engineering", topic.getTopicName());
    }
}