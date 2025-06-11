package com.unir.relatospapel.msgateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining beans related to object mapping.
 * This class provides a bean for ObjectMapper which can be used for JSON serialization and deserialization.
 */
@Configuration
public class MapperConfig {

    /**
     * Bean definition for ObjectMapper.
     * This bean can be used to serialize and deserialize JSON data.
     *
     * @return a new instance of ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {;
        return new ObjectMapper();
    }
}
