package com.example.java.demo.service;


import com.example.java.demo.Repostries.ConsumerRepo;
import com.example.java.demo.model.Consumer_Data;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final ConsumerRepo consumerRepo;

    public ConsumerService(ConsumerRepo consumerRepo) {
        this.consumerRepo = consumerRepo;
    }

    public void saveConsumer(Consumer_Data consumerData) {
       consumerRepo.save(consumerData);

    }
}
