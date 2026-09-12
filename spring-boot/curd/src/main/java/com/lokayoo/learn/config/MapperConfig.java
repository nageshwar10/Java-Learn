package com.lokayoo.learn.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration for object mapping.
 */
@Configuration
public class MapperConfig {

    /**
     * Creates the shared ModelMapper bean used to convert
     * entities into DTOs and request DTOs into entities.
     *
     * @return configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
