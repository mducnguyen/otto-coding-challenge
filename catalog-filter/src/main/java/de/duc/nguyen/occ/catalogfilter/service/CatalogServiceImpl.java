package de.duc.nguyen.occ.catalogfilter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.duc.nguyen.occ.catalogfilter.api.CatalogApi;
import de.duc.nguyen.occ.catalogfilter.models.AbstractNode;
import de.duc.nguyen.occ.catalogfilter.models.Catalog;
import de.duc.nguyen.occ.catalogfilter.models.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogApi catalogApi;

    @Override
    public List<Link> getLinks() throws JsonProcessingException {
        Catalog catalog = getCatalog();
        return getLinksOf(catalog);
    }

    @Override
    public List<Link> getLinks(String parent) throws JsonProcessingException {
        Catalog catalog = getCatalog();

        List<AbstractNode> nodes = extractNodes(catalog, parent);

        return getLinkOf(nodes);
    }

    private List<AbstractNode> extractNodes(Catalog catalog, String label) {
        List<AbstractNode> navigationEntries = catalog.getNavigationEntries();

        return navigationEntries.stream()
                .flatMap(node -> node.findFirstChildrenToHaveLabel(label).stream())
                .peek(node -> node.setParentForChildren(null))
                .collect(Collectors.toList());

    }

    private List<Link> getLinkOf(List<AbstractNode> nodes) {
        return nodes.stream()
                .flatMap(section -> section.getAllLeaves().stream().map(leaf -> (Link) leaf))
                .collect(Collectors.toList());
    }

    private Catalog getCatalog() throws JsonProcessingException {

        String catalogJsonString = this.catalogApi.getCatalog();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(catalogJsonString);
        Catalog catalog = mapper.treeToValue(jsonNode, Catalog.class);
        catalog.initParentForNodes();

        return catalog;
    }

    private List<Link> getLinksOf(Catalog catalog) {
        return getLinkOf(catalog.getNavigationEntries());
    }


}
