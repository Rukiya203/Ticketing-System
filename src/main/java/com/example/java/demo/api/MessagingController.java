package com.example.java.demo.api;

import com.example.java.demo.model.Test5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Controller
public class MessagingController {
   @Autowired
    private Test5 test5;

    // WebSocket endpoint
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessageWebSocket(String message) {

        test5.sendManualMessage("/topic/messages","halo");
        // Log the incoming message for debugging
        System.out.println("Received WebSocket message: " + message);

        // Respond with a message
        return "Hello, User! This is a WebSocket message: " + message;
    }

//        // REST endpoint
//        @GetMapping("/sendMessage")
//        public String sendMessageRest() {
//            String destination = "/topic/messages";
//            String message = "Hello, User! This is a manual message.";
//            test5.sendManualMessage(destination, message);
//            return "Message sent to: " + destination;
//        }
}
