package com.example.java.demo.model;

import java.util.*;

public class Event5 {

    private UUID eventId; // Unique event ID
    private String location;
    private List<Ticket> ticketPool;


    public Event5(String location) {
        this.eventId = UUID.randomUUID(); // Generate a unique ID
        this.location = location;
        this.ticketPool = Collections.synchronizedList(new ArrayList<>());
    }

    // Getter and Setter for eventId
    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for ticketPool
    public List<Ticket> getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(List<Ticket> ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Method to get the size of the ticket pool
    public int getTicketPoolSize() {
        return ticketPool != null ? ticketPool.size() : 0;
    }

    // Method to add tickets for the event
    public synchronized void addTickets(int numberOfTickets) {
        for (int i = 0; i < numberOfTickets; i++) {
            Ticket ticket = new Ticket(UUID.randomUUID(), "Ticket" + (i + 1), "VIP", "Available");
            ticketPool.add(ticket);
        }
    }

    // Method to remove a ticket from the event
    public  synchronized  Ticket removeTicket(UUID eventId) {
        Ticket ticket = null;
        if (this.eventId.equals(eventId) && !ticketPool.isEmpty()) {
            ticket = ticketPool.remove(0);  // Remove the first ticket
            System.out.println("Ticket removed: " + ticket);
            System.out.println("capacity is "+this.ticketPool.size());
        } else {
            System.out.println("No tickets available or event ID mismatch.");
        }
        return ticket;
    }

    // Method to display event information
    @Override
    public String toString() {
        return "Event5{" +
                "eventId=" + eventId +
                ", location='" + location + '\'' +
                ", ticketPool=" + ticketPool.size() +
                '}';
    }

    public static void main(String[] args) {
        // Create sample events
        Event5 event1 = new Event5("Colombo");
        Event5 event2 = new Event5("Kandy");

//        // Add some tickets to the events
//        event1.addTickets(5);  // Add 5 tickets to event1
//        event2.addTickets(3);  // Add 3 tickets to event2

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display available events to the user
            System.out.println("Available Events:");
            System.out.println("1. " + event1.getLocation() + " (Event ID: " + event1.getEventId() + ")");
            System.out.println("2. " + event2.getLocation() + " (Event ID: " + event2.getEventId() + ")");
            System.out.println("Enter the event ID to add or remove tickets:");

            // Prompt the user for the event ID
            String input = scanner.nextLine();
            try {
                UUID eventId = UUID.fromString(input);

                // Match the input eventId to one of the events
                if (eventId.equals(event1.getEventId())) {
                    System.out.println("Enter the number of tickets to add to event1 (Colombo):");
                    int ticketsToAdd = Integer.parseInt(scanner.nextLine());
                    event1.addTickets(ticketsToAdd);
                    System.out.println(ticketsToAdd + " tickets added to event1 (Colombo).");
                    System.out.println("Total tickets in event1: " + event1.getTicketPoolSize());
                } else if (eventId.equals(event2.getEventId())) {
                    System.out.println("Enter the number of tickets to add to event2 (Kandy):");
                    int ticketsToAdd = Integer.parseInt(scanner.nextLine());
                    event2.addTickets(ticketsToAdd);
                    System.out.println(ticketsToAdd + " tickets added to event2 (Kandy).");
                    System.out.println("Total tickets in event2: " + event2.getTicketPoolSize());
                } else {
                    System.out.println("Event ID not found. Please try again.");
                    continue;
                }

                // Option to remove tickets
                System.out.println("Do you want to remove a ticket from the event? (yes/no)");
                String removeInput = scanner.nextLine();
                if ("yes".equalsIgnoreCase(removeInput)) {
                    if (eventId.equals(event1.getEventId())) {
                        event1.removeTicket(eventId);  // Remove a ticket from event1
                    } else if (eventId.equals(event2.getEventId())) {
                        event2.removeTicket(eventId);  // Remove a ticket from event2
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid UUID format. Please try again.");
            }
        }
    }
}
