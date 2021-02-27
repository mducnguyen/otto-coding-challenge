package de.duc.nguyen.occ.catalogfilter.service.sort;

import com.google.common.collect.ComparisonChain;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortInvalidPropertyException;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SortServiceImpl implements SortService {

    private final SortStrategyFactory sortStrategyFactory;

    private final SortPropertiesParser sortPropertiesParser;

    @Override
    public void sort(List<LinkDto> linkDTOS, String sortOption) throws SortInvalidPropertyException {
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);
        sort(linkDTOS, sortProperties);
    }


    private void sort(List<LinkDto> linkDTOS, SortProperties sortProperties) {
        List<SortStrategy> sortStrategies = sortStrategyFactory.getSortStrategies(sortProperties);

        linkDTOS.sort((link1, link2) -> {

            ComparisonChain comparisonChain = ComparisonChain.start();

            for (SortStrategy sortStrategy : sortStrategies) {
                comparisonChain = sortStrategy.chain(comparisonChain, link1, link2);
            }

            return comparisonChain.result();

        });
    }

}
