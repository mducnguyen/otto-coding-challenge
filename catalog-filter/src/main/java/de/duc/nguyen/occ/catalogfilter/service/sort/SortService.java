package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDto;

import java.util.List;

public interface SortService {
    void sort(List<LinkDto> linkDTOs, String sortOption);
}
