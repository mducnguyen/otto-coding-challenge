package de.duc.nguyen.occ.catalogfilter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @Bean
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
