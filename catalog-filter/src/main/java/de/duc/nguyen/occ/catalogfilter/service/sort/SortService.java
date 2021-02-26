package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDTO;

import java.util.List;

public interface SortService {
    void sort(List<LinkDTO> linkDTOS, String sortOption);
}
