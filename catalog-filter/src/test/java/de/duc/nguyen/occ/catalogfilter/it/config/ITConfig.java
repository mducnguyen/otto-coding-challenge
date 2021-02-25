package de.duc.nguyen.occ.catalogfilter.it.config;

import de.duc.nguyen.occ.catalogfilter.api.CatalogApi;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("integration-test")
@Configuration
public class ITConfig {

    @MockBean
    CatalogApi catalogApi;
}
