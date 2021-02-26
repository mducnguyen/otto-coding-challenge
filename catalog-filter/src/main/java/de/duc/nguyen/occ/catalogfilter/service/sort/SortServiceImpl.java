package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDTO;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SortServiceImpl implements SortService {

    private final SortStrategyFactory sortStrategyFactory;

    @Override
    public void sort(List<LinkDTO> linkDTOS, String sortOption) {
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);
        sort(linkDTOS, sortProperties);
    }

    private void sort(List<LinkDTO> linkDTOS, SortProperties sortProperties) {
        linkDTOS.sort((link1, link2) -> sortStrategyFactory.getSortStrategy(sortProperties, link1, link2).result());
    }

}
