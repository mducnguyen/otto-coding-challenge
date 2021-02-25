package de.duc.nguyen.occ.catalogfilter.api;

import de.duc.nguyen.occ.catalogfilter.config.CatalogApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CatalogApiImpl implements CatalogApi {

    public static final String X_API_KEY = "x-api-key";

    private final RestTemplate restTemplate;

    private final CatalogApiParam catalogApiParam;


    @Override
    public String getCatalog() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(X_API_KEY, catalogApiParam.getApiKey());

        final HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        return this.restTemplate.exchange(catalogApiParam.getUrl(), HttpMethod.GET, httpEntity, String.class).getBody();
    }

}
