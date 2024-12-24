package com.example.java.demo.CLI;

import com.example.java.demo.model.Ticket;
import com.example.java.demo.model.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class TicketPool2 {

    /**
     * Synchronized list of tickets in the pool.
     * <p>
     * This list is thread-safe and can be accessed by multiple threads.
     */
    private final List<Ticket> ticketList;
    private static final Logger logger = LoggerFactory.getLogger(TicketPool2.class);
    private final Configuration configuration;
    private int ticketsProduced = 0;
    private int ticketsConsumed = 0;

    /**
     * Constructor for TicketPool
     * <p>
     * This constructor takes a configuration object and creates a synchronized list of tickets.
     * The list is initialized with the capacity of the configuration's maximum ticket capacity.
     * The total tickets produced is set to 0.
     *
     * @param configuration the configuration to use
     */
    public TicketPool2(Configuration configuration) {

        this.ticketList = Collections.synchronizedList(new ArrayList<>());
        this.configuration = configuration;
    }



    public void allTickts(){
        for(Ticket ticket:ticketList){
            System.out.println(ticket);
        }
    }

    /**
     * Producer adds tickets to the pool.
     * <p>
     * This method is thread-safe and blocks until the pool has available capacity.
     * It also blocks if the total number of tickets produced has reached the maximum.
     *
     * @param ticket the ticket to add
     * @return true if the ticket was added, false otherwise
     */


    public boolean addTicket(Ticket ticket) {
        synchronized (ticketList) {
            while (ticketList.size() >= configuration.getMaxTicketCapacity() || ticketsProduced >= configuration.getTotalTickets()) {
                if (ticketsProduced >= configuration.getTotalTickets()) {
                    return false;
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
            logger.info("{} added ticket: {} | Tickets Produced: {} | Current Pool Size: {}",
                    Thread.currentThread().getName(), ticket, ticketsProduced, ticketList.size());
            ticketList.notifyAll();
            return true;
        }
    }


    /**
     * Removes a ticket from the pool.
     * <p>
     * This method is thread-safe and blocks until the pool has available tickets.
     * It also blocks if the total number of tickets produced has been reached and the pool is empty.
     *
     * @return the removed ticket or null if the pool is empty
     */




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
            logger.info("{} removed ticket: {} | Tickets Consumed: {} | Current Pool Size: {}",
                    Thread.currentThread().getName(), ticket, ticketsConsumed, ticketList.size());
            ticketList.notifyAll();
            return ticket;
        }
    }

    public Ticket removed(){
        Ticket ticket = ticketList.remove(0);

        return ticket;
    }







    /**
     * Returns the total number of tickets produced.
     *
     * @return the total number of tickets produced
     */

    public synchronized int getTicketsProduced() {
        return ticketsProduced;
    }


    public synchronized int getTicketsConsumed() {
        return ticketsConsumed;
    }

    /**
     * Returns the list of tickets in the pool.
     *
     * @return the list of tickets in the pool
     */
    public synchronized List<Ticket> getTicketList() {
        return new ArrayList<>(ticketList);
    }

    public synchronized int getRemainingTickets() {
        return ticketList.size();
    }

}
