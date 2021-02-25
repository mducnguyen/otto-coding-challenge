package de.duc.nguyen.occ.catalogfilter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api")
@Data
public class CatalogApiParam {

    private String url;
    private String apiKey;

}
