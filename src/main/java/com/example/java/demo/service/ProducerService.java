package com.example.java.demo.service;

import com.example.java.demo.Repostries.Producer2Repo;
import com.example.java.demo.model.Producer3;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final Producer2Repo producer2Repo;




    public ProducerService(Producer2Repo producer2Repo) {
        this.producer2Repo = producer2Repo;


    }

    public void saveVendor(Producer3 producer3) {
       producer2Repo.save(producer3);

    }

//    public void deleteTicketById(String ticketId) {
//        ticketRepo.delete(ticketId);
//    }

//    public List<Producer2> getAllTickets() {
//        return producer2Repo.findAll();
//    }




}
