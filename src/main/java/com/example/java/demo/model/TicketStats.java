package com.example.java.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_stats")
public class TicketStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ticketsProduced;
    private int ticketsConsumed;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTicketsProduced() {
        return ticketsProduced;
    }

    public void setTicketsProduced(int ticketsProduced) {
        this.ticketsProduced = ticketsProduced;
    }

    public int getTicketsConsumed() {
        return ticketsConsumed;
    }

    public void setTicketsConsumed(int ticketsConsumed) {
        this.ticketsConsumed = ticketsConsumed;
    }
}
