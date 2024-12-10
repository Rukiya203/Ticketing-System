package com.example.java.demo.Repostries;

import com.example.java.demo.model.Producer3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Producer2Repo extends JpaRepository<Producer3, UUID> {
}
