package com.FCM.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FcmConfig {

    @PostConstruct
    public void initialize() {
        try {

            /*
            get it form https://console.firebase.google.com/u/0/project/{project_id}/settings/serviceaccounts/adminsdk
             */
            FileInputStream serviceAccount = new FileInputStream("fir-fcm.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
