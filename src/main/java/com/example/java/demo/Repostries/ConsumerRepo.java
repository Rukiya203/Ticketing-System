package com.example.java.demo.Repostries;

import com.example.java.demo.model.Consumer_Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsumerRepo extends JpaRepository<Consumer_Data, UUID> {
}
