package de.duc.nguyen.occ.catalogfilter.api;

import de.duc.nguyen.occ.catalogfilter.config.CatalogApiParam;
import de.duc.nguyen.occ.catalogfilter.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatalogApiTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    CatalogApiParam catalogApiParam;

    CatalogApi catalogApi;

    @Before
    public void init() {
        catalogApi = new CatalogApiImpl(restTemplate, catalogApiParam);
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetCatalog_thenReturnCatalogAsJsonString() {
        when(catalogApiParam.getApiKey()).thenReturn("apiKey");
        when(catalogApiParam.getUrl()).thenReturn("apiUrl");
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(ResponseEntity.of(Optional.of(TestUtils.getTestCatalogJson())));

        String catalog = catalogApi.getCatalog();
        assertThat(catalog.isEmpty(), is(false));
        assertThat(TestUtils.isJsonString(catalog), is(true));
    }
}