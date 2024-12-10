package com.example.java.demo.CLI;

import com.example.java.demo.DTO.Ticket;

public class Consumer implements Runnable {
    private final TicketPool2 ticketPool;
    private final int customerRetrievalRate;


    public Consumer(TicketPool2 ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }



    @Override
    public void run() {
        while (true) {
            Ticket ticket = ticketPool.removeTicket();
            if (ticket == null) {
                System.out.println(Thread.currentThread().getName() + " has no more tickets to consume.");
                break;
            }
            try {
                Thread.sleep(customerRetrievalRate * 1000); // Simulate delay in processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
