package com.example.java.demo.api;


import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.model.Configuration;
import com.example.java.demo.model.Event;
import com.example.java.demo.model.Event5;
import com.example.java.demo.model.Ticket;
import com.example.java.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@RequestMapping("api/v1/event")
@RestController
@CrossOrigin("*")










































































































public class EventController {
    @Autowired
    private EventService eventService;

    private static Event5 event5;

    @PostMapping
    public ResponseEntity<Map<String, Object>> AddEvent(
            @RequestParam String location,@RequestParam int total_tickets) {

        Map<String, Object> response = new HashMap<>();





       eventService.addTickets(location,total_tickets);
//       eventService.AddEvent(event_name,location,ticket_name);
        response.put("message", "All good");
        response.put("location", location);
        int value = eventService.poolSize();
        response.put("total_tickets",value);
//        event5 = new Event5(location);
        UUID value3 = eventService.getId();
        response.put("eventId",value3);






        return ResponseEntity.ok(response);

    }

//   static Configuration configuration = new Configuration(25,2,3,25,10,15);
//   static TicketPool2 ticketPool2 = new TicketPool2(configuration);
//   static Event event = new Event(ticketPool2, configuration,"Holy");
//
//
//
//    @PostMapping
//    public synchronized ResponseEntity<Map<String, Object>> AddTickets() {
//
//
//        event.addTFEvent();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Event added successfully");
//        response.put("event_capacity",event.getConfiguration().getMaxTicketCapacity());
//        response.put("event_name",event.getEvent_name());
////        response.put("ticket_id", ticket.getTicketId());
////        response.put("name", ticket.getTicketName());
////        response.put("event_name", ticket.getEventName());
////        response.put("location", ticket.getLocation());
//
//
//
//
//
//        return ResponseEntity.ok(response);
//
//    }

    @GetMapping("/{eventId}")
    public   synchronized ResponseEntity<Map<String, Object>>  getTicket(@PathVariable UUID eventId) {

        if(eventService.getId().equals(eventId)){
            eventService.RemoveTicket(eventId);

        }
//        eventService.RemoveTicket(event_id);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "All good");
        response.put("capacity",eventService.poolSize());
        response.put("eventId",eventService.getId());
        response.put("location",eventService.location());





        return ResponseEntity.ok(response);

    }


}
