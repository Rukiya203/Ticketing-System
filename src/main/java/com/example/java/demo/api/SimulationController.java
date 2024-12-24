package com.example.java.demo.api;

import com.example.java.demo.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin("*")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @PostMapping("/start")
    /**
     * Starts the simulation with the provided parameters.
     *
     * @param totalTickets          the total number of tickets to produce
     * @param ticketReleaseRate     the rate at which tickets are released (tickets per second)
     * @param customerRetrievalRate the rate at which customers retrieve tickets (tickets per second)
     * @param maxTicketCapacity     the maximum capacity of the ticket pool
     * @param numProducers          the number of producer threads
     * @param numConsumers          the number of consumer threads
     * @return a response entity containing a message indicating the status of the operation
     */
    public ResponseEntity<Map<String, Object>> startSimulation(
            @RequestParam int totalTickets,
            @RequestParam int ticketReleaseRate,
            @RequestParam int customerRetrievalRate,
            @RequestParam int maxTicketCapacity,
            @RequestParam int numProducers,
            @RequestParam int numConsumers) {

        Map<String, Object> response = new HashMap<>();



        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 || maxTicketCapacity <= 0 || numProducers <= 0 || numConsumers <= 0) {
            response.put("message", "Fill all inputs");

        }


        simulationService.startSimulation(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numProducers, numConsumers);
        response.put("message", "All good");



        return ResponseEntity.ok(response);

    }

    @PostMapping("/stop")

    public void  stopSimulation() {

       simulationService.stopSimulation();
    }

    @GetMapping("/stats")
    public Map<String, Object> getTicketStats() {
       return simulationService.sendTicketStats();
    }


}
