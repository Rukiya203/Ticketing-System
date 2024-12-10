package com.example.java.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Method to send notifications
    public void sendNotification(String destination, Object message) {
        // Sending a message to WebSocket clients subscribed to a topic
        messagingTemplate.convertAndSend(destination, message);
    }
}
