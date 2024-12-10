package com.example.java.demo.CLI;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


public class Configuration2 {







    private final int maxTicketCapacity;
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerRetrievalRate;
    private int numOfVendors;
    private int numOfConsumers;


    public int getNumOfVendors() {
        return numOfVendors;
    }

    public int getNumOfConsumers() {
        return numOfConsumers;
    }

    public void setNumOfConsumers(int numOfConsumers) {
        this.numOfConsumers = numOfConsumers;
    }

    public void setNumOfVendors(int numOfVendors) {
        this.numOfVendors = numOfVendors;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public Configuration2(int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int numOfVendors, int numOfConsumers) {
        this.numOfVendors = numOfVendors;
        this.numOfConsumers = numOfConsumers;
        if (maxTicketCapacity <= 0 || totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0) {
            throw new IllegalArgumentException("All configuration parameters must be positive integers.");
        }
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "maxTicketCapacity=" + maxTicketCapacity +
                ", totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                '}';
    }
}
