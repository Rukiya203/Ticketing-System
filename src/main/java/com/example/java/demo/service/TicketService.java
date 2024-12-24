package com.example.java.demo.service;

import com.example.java.demo.Repostries.TicketRepo3;
import com.example.java.demo.model.Ticket;
//import com.example.java.demo.Repostries.TicketRepo2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepo3 ticketRepo;


    public TicketService(TicketRepo3 ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public void saveTicket(Ticket ticket) {
        ticketRepo.save(ticket);
    }

    public void deleteTicketById(Ticket ticket) {
        ticketRepo.delete(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
}
