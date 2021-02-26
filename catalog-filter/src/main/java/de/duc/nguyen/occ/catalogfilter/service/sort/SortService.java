package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;

import java.util.List;

public interface SortService {
    void sort(List<LinkDto> linkDTOS, String sortOption);
}
