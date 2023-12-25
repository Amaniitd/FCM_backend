package com.FCM.demo;

import org.springframework.stereotype.Service;

@Service
public class FcmService {

    private static final String DEFAULT_TOPIC = "test";

    public String determineTopicForUser(String userId) {
        // Logic to determine the topic
        return DEFAULT_TOPIC;
    }
}

