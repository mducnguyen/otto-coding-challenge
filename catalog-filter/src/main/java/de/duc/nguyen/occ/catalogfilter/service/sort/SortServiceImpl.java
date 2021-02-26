package de.duc.nguyen.occ.catalogfilter.service.sort;

import com.google.common.collect.ComparisonChain;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SortServiceImpl implements SortService {

    private final SortStrategyFactory sortStrategyFactory;

    @Override
    public void sort(List<LinkDto> linkDTOS, String sortOption) {
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);
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
