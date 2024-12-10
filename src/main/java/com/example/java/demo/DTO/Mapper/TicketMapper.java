package com.example.java.demo.DTO.Mapper;

import com.example.java.demo.DTO.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {

    // Converts a Ticket entity to a Ticket DTO
    public static Ticket toDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return new Ticket(
                ticket.getTicketId(),
                ticket.getTicketName(),
                ticket.getEventName(), // Fixed incorrect duplicate mapping
                ticket.getLocation()
        );
    }

    // Converts a Ticket DTO to a Ticket entity
    public static Ticket toEntity(Ticket ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDTO.getTicketId()); // Optional, include if required
        ticket.setTicketName(ticketDTO.getTicketName());
        ticket.setEventName(ticketDTO.getEventName());
        ticket.setLocation(ticketDTO.getLocation());
        return ticket;
    }
}
