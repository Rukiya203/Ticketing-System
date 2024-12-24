package com.example.java.demo.service;

import com.example.java.demo.CLI.Consumer;
import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.CLI.Vendor;
import com.example.java.demo.TicketPool;
import com.example.java.demo.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SimulationService {

    @Autowired

    /**
     * The ticket pool service.
     */
    private TicketPool2 ticketpool2;
    private TicketPool ticketPool;
    private final TicketService ticketService;
    private static final List<Thread> threadList = new ArrayList<>();// For WebSocket communication
    /**
     * The list of threads for the simulation, used for stopping the simulation.
     */
    public SimulationService(TicketService ticketService) {


        this.ticketService = ticketService;
    }

    public void startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int numProducers, int numConsumers) {

        /**
         * Starts the simulation with the provided parameters.
         *
         * @param totalTickets          the total number of tickets to produce
         * @param ticketReleaseRate     the rate at which tickets are released (tickets per second)
         * @param customerRetrievalRate the rate at which customers retrieve tickets (tickets per second)
         * @param maxTicketCapacity     the maximum capacity of the ticket pool
         * @param numProducers          the number of producer threads
         * @param numConsumers          the number of consumer threads
         */
          Configuration configuration1 = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate,maxTicketCapacity,numProducers,numConsumers);
        ticketpool2=new TicketPool2(configuration1);
        ticketPool = new TicketPool(ticketService,configuration1);

        ticketPool.updateQueueCapacity(maxTicketCapacity);


        for (int i = 1; i <= numProducers; i++) {
            Thread producer = new Thread(new Vendor(ticketpool2, ticketReleaseRate), "Producer-" + i);
            producer.start();
            threadList.add(producer);

        }

        for (int i = 1; i <= numConsumers; i++) {
            Thread consumer = new Thread(new Consumer(ticketpool2, customerRetrievalRate), "Consumer-" + i);
            consumer.start();
            threadList.add(consumer);
        }
    }

    /**
     * Returns a map of the current ticket stats.
     *
     * @return a map with the following key-value pairs:
     *         "totalTickets" : the total number of tickets produced
     *         "ticketsInPool": the number of tickets currently in the pool
     *         "ticketsRetrieved": the number of tickets retrieved by customers
     */


    public void stopSimulation() {

        for (Thread thread : threadList) {
            thread.interrupt();
        }
        System.out.println("All threads stopped");
        threadList.clear();
    }




    /**
     * Sends ticket stats by fetching the number of tickets produced and consumed.
     *
     * @return a map containing the ticket statistics
     *         - "ticketsProduced": the number of tickets produced
     *         - "ticketsConsumed": the number of tickets consumed
     *
     * Prints error messages if ticketpool2 is null or if an exception occurs during the process.
     */

    public Map<String, Object>  sendTicketStats() {
        if (ticketpool2 == null) {
            System.err.println("Error: ticketpool2 is null. Unable to fetch ticket stats.");

        }

        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("ticketsProduced", ticketpool2.getTicketsProduced());
            stats.put("ticketsConsumed", ticketpool2.getTicketsConsumed());

            System.out.println("Statss are  : " + stats);

            return stats;





        } catch (Exception e) {
            System.err.println("An error occurred while fetching ticket stats: " + e.getMessage());
            e.printStackTrace(); // Log stack trace for debugging
        }


        return Map.of();
    }





}
