package com.example.java.demo.CLI;

import com.example.java.demo.Repostries.TicketStatsRepository;
import com.example.java.demo.DTO.Ticket;
import com.example.java.demo.model.Configuration;
import com.example.java.demo.model.TicketStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TicketPool2 {

//    @Autowired
//    private TicketStatsRepository ticketStatsRepository;

    private final List<Ticket> ticketList;
    private static final Logger logger = LoggerFactory.getLogger(TicketPool2.class);
    private final Configuration configuration;
    private int ticketsProduced = 0;
    private int ticketsConsumed = 0;

    public TicketPool2(Configuration configuration) {
        this.ticketList = Collections.synchronizedList(new ArrayList<>());
        this.configuration = configuration;
    }

    // Producer adds tickets to the pool
    public boolean addTicket(Ticket ticket) {
        synchronized (ticketList) {
            while (ticketList.size() >= configuration.getMaxTicketCapacity() || ticketsProduced >= configuration.getTotalTickets()) {
                if (ticketsProduced >= configuration.getTotalTickets()) {
                    return false; // All tickets have been produced

                }
                try {
                    logger.info("{} is waiting to add tickets...", Thread.currentThread().getName());
                    ticketList.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
            ticketList.add(ticket);
            ticketsProduced++;
//            saveStatsToDatabase();
            logger.info("{} added ticket: {} | Tickets Produced: {} | Current Pool Size: {}",
                    Thread.currentThread().getName(), ticket, ticketsProduced, ticketList.size());
            ticketList.notifyAll();
            return true;
        }
    }

    // Consumer removes a ticket from the pool
    public Ticket removeTicket() {
        synchronized (ticketList) {
            while (ticketList.isEmpty()) {
                try {
                    logger.info("{} is waiting for tickets...", Thread.currentThread().getName());
                    ticketList.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            Ticket ticket = ticketList.remove(0);
            ticketsConsumed++;
//            saveStatsToDatabase();
            logger.info("{} removed ticket: {} | Tickets Consumed: {} | Current Pool Size: {}",
                    Thread.currentThread().getName(), ticket, ticketsConsumed, ticketList.size());
            ticketList.notifyAll();
            return ticket;
        }
    }

    // Get total tickets left to be consumed
    public synchronized int getTotalTicketsLeft() {
        return ticketsProduced - ticketsConsumed;
    }

    // Return a copy of the current ticket list
    public synchronized List<Ticket> getTicketList() {
        return new ArrayList<>(ticketList);
    }

    // Getters for produced and consumed tickets
    public synchronized int getTicketsProduced() {
        return ticketsProduced;
    }

    public synchronized int getTicketsConsumed() {
        return ticketsConsumed;
    }

    public synchronized int getRemainingTickets() {
        return ticketList.size();
    }

//    // Save stats to the database
//    private synchronized void saveStatsToDatabase() {
//        if (ticketStatsRepository != null) {
//            TicketStats stats = new TicketStats();
//            stats.setTicketsProduced(ticketsProduced);
//            stats.setTicketsConsumed(ticketsConsumed);
//            ticketStatsRepository.save(stats);
//            logger.info("Saved stats to database: {}", stats);
//        } else {
//            logger.warn("TicketStatsRepository is not initialized. Skipping database save.");
//        }
//    }

    @Override
    public String toString() {
        return "TicketPool2{" +
                "ticketList=" + ticketList.size() +
                ", ticketsProduced=" + ticketsProduced +
                ", ticketsConsumed=" + ticketsConsumed +
                '}';
    }
}
