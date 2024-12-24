package com.example.java.demo.api;

import com.example.java.demo.model.Ticket;
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
@CrossOrigin("*")

public class TicketContoller {

    @Autowired
    /**
     * TicketPool is a thread-safe pool of tickets.
     * It is used to store and retrieve tickets.
     */
    private final TicketPool ticketPool;
    private TicketService ticketService;





    /**
     * Creates a new instance of TicketContoller.
     * @param ticketPool The ticket pool that will be used to store and retrieve tickets.
     * @param ticketService The service that will be used to save and delete tickets.
     */
    public TicketContoller(TicketPool ticketPool,TicketService ticketService) {
        this.ticketPool = ticketPool;
        this.ticketService=ticketService;




    }

    @PostMapping
    /**
     * Adds a ticket to the pool.
     * <p>
     * This method is thread-safe and blocks until the pool has available capacity.
     * It also blocks if the total number of tickets produced has reached the maximum.
     *
     * @param ticket the ticket to add
     * @return a response with the message "Ticket added successfully" if the ticket was added, otherwise a response with the message "Error: unable to add ticket to pool" if the ticket was not added
     */
    public synchronized ResponseEntity<Map<String, Object>> AddTickets(@RequestBody Ticket ticket) {


        ticketPool.addTicket(ticket);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Ticket added successfully");
        response.put("ticket_id", ticket.getTicketId());
        response.put("name", ticket.getTicketName());
        response.put("event_name", ticket.getEventName());
        response.put("location", ticket.getLocation());





        return ResponseEntity.ok(response);

    }

    @GetMapping("/{ticket_id}")
public synchronized ResponseEntity<Map<String, Object>> getTicket(@PathVariable UUID ticket_id) {
    System.out.println("GET request received for ticket ID: " + ticket_id);

    Ticket ticket = ticketPool.removeTicketById(ticket_id);

    Map<String, Object> response = new HashMap<>();

    if (ticket != null) {
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


    /**
     * Returns a list of all tickets in the pool.
     * <p>
     * This method is thread-safe.
     *
     * @return a response with a list of all tickets in the pool
     */

    @GetMapping("/available")
    public synchronized ResponseEntity<List<Ticket>> listickets() {



        List<Ticket> availbleTicket = ticketPool.ticketList();


        return ResponseEntity.ok(availbleTicket);


    }




}
