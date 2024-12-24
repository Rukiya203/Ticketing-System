package com.example.java.demo.service;

import com.example.java.demo.CLI.Consumer;
import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.CLI.Vendor;
import com.example.java.demo.TicketPool;
import com.example.java.demo.model.Configuration;
import com.example.java.demo.model.Event;
import com.example.java.demo.model.Event5;
import com.example.java.demo.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EventService {

   static  Configuration configuration = new Configuration(25,2,3,25,10,15);
//   static TicketPool2 ticketPool2 = new TicketPool2(configuration);









//    public Map<String, Object> AddEvent(String location) {
//        Configuration configuration = new Configuration(25,2,3,25,10,15);
//        TicketPool2 ticketPool2 = new TicketPool2(configuration);
//         event = new Event(ticketPool2,configuration,event_name,location,ticket_name);
//
//        try {
//            event.addTFEvent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Event added successfully");
//            response.put("event_capacity",event.getConfiguration().getMaxTicketCapacity());
//            response.put("event_name",event.getEvent_name());
//
//
//
//
//
//
//
//
//
//        } catch (Exception e) {
//            System.err.println("An error occurred while fetching ticket stats: " + e.getMessage());
//            e.printStackTrace(); // Log stack trace for debugging
//        }
//
//
//        return Map.of();
    static Event5 event5;
//    }

    public Map<String,Object> addTickets(String location,int total_tickets){
         event5 = new Event5(location);
        System.out.println(event5.getEventId());

            event5.addTickets(total_tickets);
        System.out.println(event5.getTicketPoolSize());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Event added for ticket successfully");
            response.put("event_capacity", event5.getTicketPool().size());
            response.put("total_tickets", total_tickets);
            response.put("location", location);
            response.put("id",event5.getEventId());



        return Map.of();
    }


    public  Map<String, Object> RemoveTicket(UUID event_id){

        event5.removeTicket(event_id);







            Map<String, Object> response = new HashMap<>();



//                response.put("ticket_id", ticket.getTicketId());
                response.put("event_capacity", event5.getTicketPoolSize());


        return response;
    }

    public int poolSize(){
        return event5.getTicketPoolSize();
    }

    public UUID getId(){
        return event5.getEventId();
    }


    public String location(){
        return event5.getLocation();
    }




    }




