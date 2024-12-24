package com.example.java.demo.CLI;

import com.example.java.demo.model.Ticket;
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
        while (!Thread.currentThread().isInterrupted()) {
            Ticket ticket = new Ticket(UUID.randomUUID(), "Casino ", "GrandBell", "Galadari");
            boolean added = ticketPool.addTicket(ticket);

            if (!added) {
                break;
            }

            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
