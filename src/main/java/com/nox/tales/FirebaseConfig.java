package com.nox.tales;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;

public class FirebaseConfig {
    private static final String FIREBASE_CONFIG = "{\"type\": \"service_account\", \"project_id\": \"your-project-id\", \"private_key\": \"your-private-key\", \"client_email\": \"your-client-email\", \"client_id\": \"your-client-id\", \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\", \"token_uri\": \"https://oauth2.googleapis.com/token\", \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\", \"client_x509_cert_url\": \"your-cert-url\"}";
    
    public static void initialize() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(
                    new java.io.ByteArrayInputStream(FIREBASE_CONFIG.getBytes())))
                .build();
            
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}