package com.example.java.demo.api;

import com.example.java.demo.Repostries.TicketStatsRepository;

import com.example.java.demo.model.TicketStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class StatsController {

    @Autowired
    private TicketStatsRepository ticketStatsRepository;

    @GetMapping("/stats")
    public TicketStats getLatestStats() {
        return ticketStatsRepository.findAll().stream().reduce((first, second) -> second).orElse(new TicketStats());
    }
}
