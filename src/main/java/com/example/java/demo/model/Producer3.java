package com.example.java.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "vendor5")
public class Producer3 {

    @Id
    @Column(name = "vendor_id", unique = true, nullable = false)
    private UUID vendorId;

//    @Column(name = "ticket_release_rate", nullable = false)
//    private int ticketReleaseRate;
//
//    @Column(name = "ticket_per_release", nullable = false)
//    private int ticketPerRelease;


    @Column(name = "nic_number", unique = true, nullable = false)
    private String nicNumber;

    @Column(name = "password")
    private String password;

    // One-to-One relationship with Configuration
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "config_id", referencedColumnName = "config_id")
    private Configuration configuration;

    // Default constructor
    public Producer3() {

    }

    // Constructor
    public Producer3(@JsonProperty("vendor_id") UUID vendorId,
                     @JsonProperty("nic_number") String nicNumber,
                     @JsonProperty("password") String password,
                     @JsonProperty("config_id") Configuration configuration) {
        this.vendorId = UUID.randomUUID();
        this.nicNumber = nicNumber;
        this.password = password;
        this.configuration = configuration;
    }

    // Getters and setters
    public UUID getVendorId() {
        return vendorId;
    }

    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }




    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "Producer3{" +
                "vendorId=" + vendorId +
                ", nicNumber='" + nicNumber + '\'' +
                ", password='" + password + '\'' +
                ", configuration=" + configuration +
                '}';
    }
}
