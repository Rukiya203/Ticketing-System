package com.example.java.demo.api;

import com.example.java.demo.service.ConsumerService;
import com.example.java.demo.model.Consumer_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/v1/consumer")
@RestController
@CrossOrigin("*")
public class ConsumerController {

    private final ConsumerService consumerService;
    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping

    public ResponseEntity<Map<String, Object>> addConsumer(@RequestBody Consumer_Data consumer_data) {
        // Add error handling in case of save failure

//        producerService.saveVendor(producer3);
        consumerService.saveConsumer(consumer_data);


        // Successful response with vendor information
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Consumer added successfully");
        response.put("vendor_id", consumer_data.getCustomerId());
//        response.put("vendor_name", producer3.);

        return ResponseEntity.ok(response); // Return success response
    }



}




