package com.example.java.demo.CLI;

import com.example.java.demo.model.Ticket;

public class Consumer implements Runnable {
    private final TicketPool2 ticketPool;
    private final int customerRetrievalRate;


    public Consumer(TicketPool2 ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Ticket ticket = ticketPool.removeTicket();
            
            if (ticket == null) {
                break;
            }

            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
