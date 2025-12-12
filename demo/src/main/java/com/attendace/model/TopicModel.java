package com.attendace.model;

/**
 * Represents a topic with a name in the system.
 */
public class TopicModel {

    /** The name of the topic. */
    private String topicName;

    /**
     * Constructs a new TopicModel with the specified topic name.
     *
     * @param topicName the name of the topic
     */
    public TopicModel(String topicName) {
        this.topicName = topicName;
    }

    /**
     * Gets the name of the topic.
     *
     * @return the topic name
     */
    public String getTopicName() {
        return topicName;
    }
}
