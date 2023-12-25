package com.FCM.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    private final RestTemplate restTemplate;
    private final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";


    /*
    get it form https://console.firebase.google.com/u/0/project/{project_id}/settings/general
     */
    private final String FCM_SERVER_KEY = "";

    public NotificationService() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<String> sendNotification(Map<String, Object> notificationData) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "key=" + FCM_SERVER_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("to", "/topics/test");
        body.put("data", notificationData);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(FCM_API_URL, entity, String.class);
    }
}

