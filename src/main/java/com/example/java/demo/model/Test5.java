package com.example.java.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class Test5 {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public Test5(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendManualMessage(String destination, String message) {
        try {
            messagingTemplate.convertAndSend(destination, message);
            System.out.println("✅ Message sent to destination: " + destination);
        } catch (Exception e) {
            System.err.println("❌ Error sending message: " + e.getMessage());
        }
    }




}
