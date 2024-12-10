package com.example.java.demo.api;

import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.DTO.Ticket;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

public class WebsocketController {



    @CrossOrigin("*")


    @Controller
    public static class MessageController {
//        @MessageMapping("/sendMessage")
//        @SendTo("/topic/messages")
//        @SendTo("/topic/stats")
//        public Map<String, Object> sendMessage(TicketPool2 message) {
//            System.out.println("Received message: " + message); // Log to check messa
//            Map<String, Object> stats = new HashMap<>();
//            stats.put("ticketsProduced", message.getTicketsProduced());  // Assuming TicketPool2 has this property
//            stats.put("ticketsConsumed", message.getTicketsConsumed());  // Assuming TicketPool2 has this property
//            stats.put("currentPoolSize", message.ticketList().size());  // Assuming ticketList() is a method in TicketPool2
//            System.out.println("Sending stats is: " + stats); // Debug log to verify the message
//
//            return stats;  // Return the map directly
//        }
    }

}
