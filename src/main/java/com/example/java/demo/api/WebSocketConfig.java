package com.example.java.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("Registering STOMP endpoints...");
        registry.addEndpoint("/chat2")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
        logger.info("STOMP endpoint '/chat2' registered with allowed origin 'http://localhost:4200'");
        System.out.println("STOMP endpoint '/chat2' registered with allowed origin 'http://localhost:4200'");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        logger.info("Configuring message broker...");
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
        logger.info("Message broker configured with prefix '/app' and simple broker '/topic'.");
    }
}