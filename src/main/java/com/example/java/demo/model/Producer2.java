package com.example.java.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "vendor")
public class Producer2 {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vendorid", unique = true, nullable = false)
    private String vendorId;

    @Column(name = "ticketsperrelease", nullable = false)
    private int ticketsPerRelease;

    @Column(name = "releaseinterval", nullable = false)
    private int releaseInterval;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Column(name = "vendorname", nullable = false)
    private String vendorName;

    // Constructor for Jackson JSON Mapping
    public Producer2(  @JsonProperty("id") String vendorid,
            @JsonProperty("t_pr") int ticketsPerRelease,
                     @JsonProperty("rli") int releaseInterval,
                     @JsonProperty("name") String vendorName) {
        this.vendorId =vendorid ; // Generate a UUID
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.vendorName = vendorName;
    }

    // Default constructor for JPA
    public Producer2() {
    }

    // Getters and Setters




    public int getTicketsPerRelease() {
        return ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public int getReleaseInterval() {
        return releaseInterval;
    }

    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    // Override toString for better debugging
    @Override
    public String toString() {
        return "Producer2{" +
                "vendorId=" + vendorId +
                ", ticketsPerRelease=" + ticketsPerRelease +
                ", releaseInterval=" + releaseInterval +
                ", vendorName='" + vendorName + '\'' +
                '}';
    }
}
