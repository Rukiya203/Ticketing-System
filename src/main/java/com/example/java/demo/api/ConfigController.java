package com.example.java.demo.api;

import com.example.java.demo.model.Configuration;
import com.example.java.demo.service.ConfigurationService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/configuration")
@RestController
@CrossOrigin("*")
public class ConfigController {

    private final ConfigurationService configurationService;

    // Constructor Injection
    public ConfigController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping
    public synchronized ResponseEntity<Map<String, Object>> AddConfig(@RequestBody Configuration configuration) {
        System.out.println("Adding new configuration: " + configuration);
        // Save the configuration to the database through the service
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





//        response.put("Max ticket Capacity", configuration.getMaxTicketCapacity());
//        response.put("Consumer Count", configuration.getConsumerCount());

        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{Config_id}")
//    public synchronized ResponseEntity<Map<String, Object>> getTicket(@PathVariable UUID Config_id) {
//        System.out.println("GET request received for Config ID: " + Config_id);
//        Map<String, Object> response = new HashMap<>();
//
//        // Fetch the configuration from the database using the service
//        Optional<Configuration> configuration = Optional.ofNullable(configurationService.getConfigurationById(Config_id));
//
//        if (configuration.isPresent()) {
//            return ResponseEntity.ok(Map.of(
//                    "Config_id", configuration.get().getConfigId(),
//                    "Max ticket Capacity", configuration.get().getMaxTicketCapacity(),
//                    "Consumer Count", configuration.get().getConsumerCount()
//            ));
//        }
//
//        // If not found, return an appropriate response
//        response.put("message", "Configuration not found");
//        return ResponseEntity.status(404).body(response);  // 404 Not Found
//    }
}
