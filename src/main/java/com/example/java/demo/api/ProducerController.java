package com.example.java.demo.api;

import com.example.java.demo.model.Producer3;
import com.example.java.demo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/v1/vendor2")
@RestController
@CrossOrigin("*") // Allow cross-origin requests from any domain (adjust if necessary)
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
       this.producerService=producerService;
    }

    @PostMapping

    public ResponseEntity<Map<String, Object>> addVendor(@RequestBody Producer3 producer3) {
        // Add error handling in case of save failure

          producerService.saveVendor(producer3);


        // Successful response with vendor information
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Vendor added successfully");
        response.put("vendor_id", producer3.getVendorId());
//        response.put("vendor_name", producer3.);

        return ResponseEntity.ok(response); // Return success response
    }
}
