package com.example.java.demo.api;

import com.example.java.demo.model.Configuration;
import com.example.java.demo.service.ConfigurationService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("api/v1/configuration")
@RestController
@CrossOrigin("*")
public class ConfigController {

    private final ConfigurationService configurationService;

    public ConfigController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }


/**
 * Handles the HTTP POST request to add a new configuration.
 * This method synchronizes the creation of a new configuration and
 * persists it using the configuration service. It then returns a response
 * with the configuration details and a success message.
 *
 */

    @PostMapping
    public synchronized ResponseEntity<Map<String, Object>> AddConfig(@RequestBody Configuration configuration) {
        System.out.println("Adding new configuration: " + configuration);
        configurationService.saveConfiguration(configuration);


        Map<String, Object> response = new HashMap<>();
        response.put("message", "Config added successfully");
        response.put("Config_id", configuration.getConfigId());
        response.put("total_tickets",configuration.getTotalTickets());
        response.put("ticket_release_rate",configuration.getTicketReleaseRate());
        response.put("customer_retrieval_rate",configuration.getCustomerRetrievalRate());
        response.put("max_ticket_capacity",configuration.getMaxTicketCapacity());
        response.put("producer_count",configuration.getProducerCount());
        response.put("consumer_count",configuration.getConsumerCount());
        return ResponseEntity.ok(response);
    }

}
