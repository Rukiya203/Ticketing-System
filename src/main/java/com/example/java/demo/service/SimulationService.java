package com.example.java.demo.service;

import com.example.java.demo.CLI.Configuration2;
import com.example.java.demo.CLI.Consumer;
import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.CLI.Vendor;
import com.example.java.demo.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SimulationService {
    private final SimpMessagingTemplate messagingTemplate;
//    private WebSocketService webSocketService;
    @Autowired
    private TicketPool2 ticketpool2;
    private static final List<Thread> threadList = new ArrayList<>();// For WebSocket communication

    public SimulationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
//        this.webSocketService = webSocketService;

    }

    public void startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int numProducers, int numConsumers) {


          Configuration configuration1 = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate,maxTicketCapacity,numProducers,numConsumers);
        // Create the ticket pool
//        ticketpool2=new TicketPool2(configuration,webSocketService);
        ticketpool2=new TicketPool2(configuration1);

        // Create and start producer threads
        for (int i = 1; i <= numProducers; i++) {
            Thread producer = new Thread(new Vendor(ticketpool2, ticketReleaseRate), "Producer-" + i);
            producer.start();
            threadList.add(producer);

        }

        // Create and start consumer threads
        for (int i = 1; i <= numConsumers; i++) {
            Thread consumer = new Thread(new Consumer(ticketpool2, customerRetrievalRate), "Consumer-" + i);
            consumer.start();
            threadList.add(consumer);
        }
    }

    public void stopSimulation() {

        for (Thread thread : threadList) {
            thread.interrupt();
        }
        System.out.println("All threads stopped");
        threadList.clear();
    }





    // Directly call this method to send the ticket stats
    public Map<String, Object>  sendTicketStats() {
        if (ticketpool2 == null) {
            System.err.println("Error: ticketpool2 is null. Unable to fetch ticket stats.");

        }

        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("ticketsProduced", ticketpool2.getTicketsProduced());
            stats.put("ticketsConsumed", ticketpool2.getTicketsConsumed());
//            stats.put("currentPoolSize", ticketpool2.t.size());

            System.out.println("Statss are  : " + stats);

            return stats;





        } catch (Exception e) {
            System.err.println("An error occurred while fetching ticket stats: " + e.getMessage());
            e.printStackTrace(); // Log stack trace for debugging
        }


        return Map.of();
    }

}
