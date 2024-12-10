package com.example.java.demo.Repostries;



import com.example.java.demo.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;



public interface ConfigurationRepository extends JpaRepository<Configuration, UUID> {

}
