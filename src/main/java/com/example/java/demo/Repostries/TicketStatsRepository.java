package com.example.java.demo.Repostries;


import com.example.java.demo.model.TicketStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatsRepository extends JpaRepository<TicketStats, Long> {
}
