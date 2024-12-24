package com.example.java.demo.service;

import com.example.java.demo.Repostries.ConfigurationRepository;
import com.example.java.demo.model.Configuration;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



@Service
public class ConfigurationService {



    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    public Configuration getConfigurationById(UUID configId) {
        return configurationRepository.findById(configId).orElse(null);
    }

    public void deleteConfiguration(UUID configId) {
        configurationRepository.deleteById(configId);
    }
}
