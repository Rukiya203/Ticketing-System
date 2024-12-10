package com.example.java.demo.api;

import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.DTO.Ticket;
import com.example.java.demo.TicketPool;
import com.example.java.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequestMapping("api/v1/ticket")
@RestController
//@CrossOrigin("*",allowCredentials = false)
@CrossOrigin("*")

public class TicketContoller {

    @Autowired
    private final TicketPool ticketPool;
    private TicketService ticketService;





    public TicketContoller(TicketPool ticketPool,TicketService ticketService) {
        this.ticketPool = ticketPool;
        this.ticketService=ticketService;




    }

    @PostMapping
    public synchronized ResponseEntity<Map<String, Object>> AddTickets(@RequestBody Ticket ticket) {



//        ticketPool.addTickets(ticket);
        ticketPool.addTicket(ticket);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Ticket added successfully");
        response.put("ticket_id", ticket.getTicketId());
        response.put("name", ticket.getTicketName());
        response.put("event_name", ticket.getEventName());
        response.put("location", ticket.getLocation());





        return ResponseEntity.ok(response);

    }

//    @GetMapping("/stats")
//    public Map<String, Object> getTicketStats() {
//        Map<String, Object> stats = new HashMap<>();
//        stats.put("ticketsProduced", ticketpool2.getTicketsProduced());
//        stats.put("ticketsConsumed", ticketpool2.getTicketsConsumed());
//        stats.put("currentPoolSize", ticketPool.ticketList().size());
//        return stats;
//    }

//    @GetMapping
//    public synchronized Ticket getTicket() {
//        System.out.println("GET request received");
//        return ticketPool.removeTicket();
//    }
@GetMapping("/{ticket_id}")
public synchronized ResponseEntity<Map<String, Object>> getTicket(@PathVariable UUID ticket_id) {
    System.out.println("GET request received for ticket ID: " + ticket_id);

    // Try to retrieve the ticket from the pool based on the ticket_id
    Ticket ticket = ticketPool.removeTicketById(ticket_id);

    Map<String, Object> response = new HashMap<>();

    if (ticket != null) {
        // Ticket found, return it in the response
        response.put("message", "Ticket retrieved successfully");
        response.put("ticket_id", ticket.getTicketId());
        response.put("ticket_name", ticket.getTicketId());


        return ResponseEntity.ok(response);  // 200 OK response
    } else {
        // No ticket found with the given ticket_id, return a different message
        response.put("message", "Ticket not found with ID: " + ticket_id);
        return ResponseEntity.status(404).body(response);  // 404 Not Found response
    }
}


//    @GetMapping("/available")
//    public synchronized ResponseEntity<List<Ticket>> listickets() {
//
//
////        List<Ticket> availbleTicket = ticketPool.ticketList();
//        List<Ticket> availbleTicket = ticketService.getAllTickets();
//
//
//        return ResponseEntity.ok(availbleTicket);
//
//
//    }

    @GetMapping("/available")
    public synchronized ResponseEntity<List<Ticket>> listickets() {


//        List<Ticket> availbleTicket = ticketPool.ticketList();
        List<Ticket> availbleTicket = ticketPool.ticketList();


        return ResponseEntity.ok(availbleTicket);


    }




}
