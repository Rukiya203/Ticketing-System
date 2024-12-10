package com.example.java.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ticket_details")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private UUID ticketId;

    @Column(name = "ticket_name")
    private String ticketName;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "location")
    private String location;

    // Default constructor
    public Ticket() {
    }

    // Parameterized constructor
    public Ticket(@JsonProperty("ticket_id")UUID ticketId,
            @JsonProperty("name") String ticketName,
                  @JsonProperty("event_name") String eventName,
                  @JsonProperty("location") String location) {
        this.ticketId = UUID.randomUUID(); // Generates a random UUID
        this.ticketName = ticketName;
        this.eventName = eventName;
        this.location = location;
    }

    // Getters and Setters
    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", ticketName='" + ticketName + '\'' +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
