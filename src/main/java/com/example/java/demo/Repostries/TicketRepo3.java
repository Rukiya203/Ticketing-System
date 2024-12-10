package com.example.java.demo.Repostries;

import com.example.java.demo.DTO.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepo3 extends JpaRepository<Ticket, UUID> {
}
