package de.duc.nguyen.occ.catalogfilter.service;

import de.duc.nguyen.occ.catalogfilter.api.CatalogApi;
import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;
import de.duc.nguyen.occ.catalogfilter.models.domain.Node;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogApi catalogApi;

    @Override
    public List<Node> getLinks() {
        Catalog catalog = catalogApi.getCatalog();
        return getLinksOf(catalog);
    }

    @Override
    public List<Node> getLinks(String parent) {
        Catalog catalog = catalogApi.getCatalog();

        List<Node> nodes = extractNodes(catalog, parent);

        return getLinkOf(nodes);
    }

    private List<Node> extractNodes(Catalog catalog, String label) {
        List<Node> navigationEntries = catalog.getNavigationEntries();

        return navigationEntries.stream()
                .flatMap(node -> node.findFirstToHaveLabel(label).stream())
                .peek(node -> node.setParentForChildren(null))
                .collect(Collectors.toList());

    }

    private List<Node> getLinkOf(List<Node> nodes) {
        return nodes.stream()
                .flatMap(section -> section.getAllLeaves().stream())
                .collect(Collectors.toList());
    }


    private List<Node> getLinksOf(Catalog catalog) {
        return getLinkOf(catalog.getNavigationEntries());
    }


}
