package com.example.java.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

@Entity
@Table(name = "configuration")
public class Configuration {

    @Id
    @Column(name = "config_id") // Primary key column
    private UUID configId;

    @Column(name = "total_tickets")
    private int totalTickets;

    @Column(name = "ticket_release_rate")
    private int ticketReleaseRate;

    @Column(name = "producer_count")
    private int producerCount;

    @Column(name = "consumer_count")
    private int consumerCount;

    @Column(name = "customer_retrieval_rate")
    private int customerRetrievalRate;

    @Column(name = "max_ticket_capacity")
    private int maxTicketCapacity;

    // One-to-One relationship with Producer3
    @OneToOne(mappedBy ="configuration", cascade = CascadeType.ALL)
    private Producer3 producer;

    // Default constructor
    public Configuration() {
        this.configId = UUID.randomUUID();
    }


    
    public Configuration(@JsonProperty("total_tickets") int totalTickets,
                         @JsonProperty("ticket_release_rate") int ticketReleaseRate,
                         @JsonProperty("customer_retrieval_rate") int customerRetrievalRate,
                         @JsonProperty("max_ticket_capacity") int maxTicketCapacity,
                         @JsonProperty("producer_count") int producerCount,
                         @JsonProperty("consumer_count") int consumerCount) {
        this.configId = UUID.randomUUID();
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.producerCount = producerCount;
        this.consumerCount = consumerCount;
    }



    // Getters and Setters
    public UUID getConfigId() {
        return configId;
    }

    public void setConfigId(UUID configId) {
        this.configId = configId;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getProducerCount() {
        return producerCount;
    }

    public void setProducerCount(int producerCount) {
        this.producerCount = producerCount;
    }

    public int getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(int consumerCount) {
        this.consumerCount = consumerCount;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Producer3 getProducer() {
        return producer;
    }

    public void setProducer(Producer3 producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "configId=" + configId +
                ", totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", producerCount=" + producerCount +
                ", consumerCount=" + consumerCount +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                ", producer=" + (producer != null ? producer.getVendorId() : "null") +
                '}';
    }
}