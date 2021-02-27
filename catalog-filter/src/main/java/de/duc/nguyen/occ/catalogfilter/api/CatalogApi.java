package de.duc.nguyen.occ.catalogfilter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;

public interface CatalogApi {
    /**
     * Get {@link Catalog} from catalog-api
     *
     * @return {@link Catalog}
     * @throws JsonProcessingException Exception is thrown when catalog-api response body is invalid
     */
    Catalog getCatalog() throws JsonProcessingException;
}
