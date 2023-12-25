package com.FCM.demo;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import java.util.Collections;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
@CrossOrigin("*")
@Log4j2
public class FcmController {

    @Autowired
    private FcmService fcmService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeToTopic(@RequestBody TokenRequest tokenRequest) {
        try {
            String token = tokenRequest.getToken();
            String topic = fcmService.determineTopicForUser(token);

            log.info("Subscribing token: " + token + " to topic: " + topic);

            // Subscribe to topic
            TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
                Collections.singletonList(token),
                topic
            );
            if (response.getSuccessCount() == 0) {
                log.error("Error subscribing to topic because of: " + response.getErrors());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error subscribing to topic");
            }

            return ResponseEntity.ok("Token was subscribed to the topic: " + topic);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error subscribing to topic");
        }
    }
}

@Getter
class FcmSubscriptionRequest {
    private String token;
    private String topic;
}

@Getter
class TokenRequest {
    private String token;
}

