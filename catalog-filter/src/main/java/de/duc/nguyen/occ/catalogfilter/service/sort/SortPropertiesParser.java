package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;

public interface SortPropertiesParser {
    /**
     * Parse sortOption to {@link SortProperties}
     *
     * @param sortOptions Sort Options
     * @return {@link SortProperties}
     */
    SortProperties parseSortProperties(String sortOptions);
}
