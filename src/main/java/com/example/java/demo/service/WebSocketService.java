//package com.example.java.demo.service;
//
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class WebSocketService {
//
//
//    private final SimpMessagingTemplate simpleMessagingTemplate;
//
//    public WebSocketService(SimpMessagingTemplate simpleMessagingTemplate) {
//        this.simpleMessagingTemplate = simpleMessagingTemplate;
//    }
//
//    public void broadcastService(Map<String, Object> stats){
//        simpleMessagingTemplate.convertAndSend("/topic/stats", stats);
//
//    }
//
//
//
//}
