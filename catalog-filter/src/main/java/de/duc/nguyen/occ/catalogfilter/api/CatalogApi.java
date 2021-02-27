package de.duc.nguyen.occ.catalogfilter.api;

import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;

public interface CatalogApi {
    /**
     * Get {@link Catalog} from catalog-api
     *
     * @return {@link Catalog}
     */
    Catalog getCatalog();
}
