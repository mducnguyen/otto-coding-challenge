package de.duc.nguyen.occ.catalogfilter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;

public interface CatalogApi {
    Catalog getCatalog() throws JsonProcessingException;
}
