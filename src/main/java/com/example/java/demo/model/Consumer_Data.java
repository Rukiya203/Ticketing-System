package com.example.java.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity

@Table(name="consumer")

//@Column(name = "vendor_id", unique = true, nullable = false)
//
//private UUID vendor_id;
//@Column(name = "ticket_release_rate", nullable = false)
//private int ticket_release_rate;
//@Column(name = "ticket_per_release", nullable = false)
//private int ticket_per_release;

public class Consumer_Data{
    @Id
    @Column(name = "consumer_id", unique = true, nullable = false)
    private UUID customerId;
    @Column(name = "ticket_retrieve_rate", nullable = false)
    private int retrievalInterval;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
//    @JsonProperty("cn") String customerName

    public Consumer_Data(@JsonProperty("id") UUID customerId,@JsonProperty("ri") int retrievalInterval) {
        this.customerId = UUID.randomUUID();
        this.retrievalInterval = retrievalInterval;
//        this.customerName = customerName;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Consumer_Data(){

    }





}



