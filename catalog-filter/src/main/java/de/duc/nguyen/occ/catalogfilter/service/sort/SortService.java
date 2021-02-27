package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;

import java.util.List;

public interface SortService {

    /**
     * Sort {@link List} of {@link LinkDto} with the given sortOption
     *
     * @param linkDTOS {@link LinkDto}
     * @param sortOption Sort Options
     */
    void sort(List<LinkDto> linkDTOS, String sortOption);
}
