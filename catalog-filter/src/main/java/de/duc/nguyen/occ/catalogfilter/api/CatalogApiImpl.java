package de.duc.nguyen.occ.catalogfilter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.duc.nguyen.occ.catalogfilter.config.CatalogApiParam;
import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CatalogApiImpl implements CatalogApi {

    public static final String X_API_KEY = "x-api-key";

    private final RestTemplate restTemplate;

    private final CatalogApiParam catalogApiParam;

    @Override
    public Catalog getCatalog() throws JsonProcessingException {

        String catalogJsonString = getCatalogFromApi();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(catalogJsonString);
        Catalog catalog = mapper.treeToValue(jsonNode, Catalog.class);
        catalog.initParentForNodes();

        return catalog;
    }

    private String getCatalogFromApi() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(X_API_KEY, catalogApiParam.getApiKey());

        final HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> exchange = this.restTemplate.exchange(catalogApiParam.getUrl(), HttpMethod.GET, httpEntity, String.class);
        return exchange.getBody();
    }



}
