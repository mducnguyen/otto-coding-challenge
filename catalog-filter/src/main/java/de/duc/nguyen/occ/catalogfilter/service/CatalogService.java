package de.duc.nguyen.occ.catalogfilter.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.models.Catalog;
import de.duc.nguyen.occ.catalogfilter.models.Link;

import java.util.List;

public interface CatalogService {
    List<Link> getLinks() throws JsonProcessingException;
}
