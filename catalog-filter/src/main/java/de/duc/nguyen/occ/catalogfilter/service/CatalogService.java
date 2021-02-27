package de.duc.nguyen.occ.catalogfilter.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.models.domain.Node;

import java.util.List;

public interface CatalogService {
    /**
     * Get all Links ({@link Node}) as from the {@link de.duc.nguyen.occ.catalogfilter.models.domain.Catalog}
     *
     * @return {@link List} of Link ({@link Node})
     *
     */
    List<Node> getLinks();

    /**
     * Get Links ({@link Node}) as from the {@link de.duc.nguyen.occ.catalogfilter.models.domain.Catalog}
     * which has a parent with label in parameter parent
     *
     * @param parent parent to be filtered
     *
     * @return {@link List} of Link ({@link Node})
     */
    List<Node> getLinks(String parent);
}
