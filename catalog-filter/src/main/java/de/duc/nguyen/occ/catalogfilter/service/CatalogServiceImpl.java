package de.duc.nguyen.occ.catalogfilter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.duc.nguyen.occ.catalogfilter.api.CatalogApi;
import de.duc.nguyen.occ.catalogfilter.models.Catalog;
import de.duc.nguyen.occ.catalogfilter.models.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogApi catalogApi;

    @Override
    public List<Link> getLinks() throws JsonProcessingException {
        Catalog catalog = getCatalog();
        return getLinks(catalog);
    }

    private Catalog getCatalog() throws JsonProcessingException {

        String catalogJsonString = this.catalogApi.getCatalog();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(catalogJsonString);
        Catalog catalog = mapper.treeToValue(jsonNode, Catalog.class);
        catalog.setParentForNodes();

        return catalog;
    }

    private List<Link> getLinks(Catalog catalog) {
        return catalog.getLinks();
    }



}
