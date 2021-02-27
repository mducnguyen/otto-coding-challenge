package de.duc.nguyen.occ.catalogfilter.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.models.domain.Node;

import java.util.List;

public interface CatalogService {
    List<Node> getLinks() throws JsonProcessingException;
    List<Node> getLinks(String parent) throws JsonProcessingException;
}
