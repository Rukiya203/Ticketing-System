package com.example.java.demo.CLI;

import com.example.java.demo.DTO.Ticket;

import java.util.UUID;

public class Vendor implements Runnable {
    private final TicketPool2 ticketPool;
    private final int ticketReleaseRate;

    public Vendor(TicketPool2 ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (true) {
            // Generate a unique UUID as ticket ID
            Ticket ticket = new Ticket(UUID.randomUUID(),null,null,null);
            boolean added = ticketPool.addTicket(ticket);

            if (!added) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted. Stopping production.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " has produced all tickets.");
                }
                break; // Stop producing when all tickets are produced or interrupted
            }

            try {
                Thread.sleep(ticketReleaseRate * 1000); // Simulate delay in processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Exit if interrupted
            }
        }
    }
}

