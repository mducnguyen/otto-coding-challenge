package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperty;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortableField;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.LabelStrategy;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.UrlStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SortStrategyFactoryImpl implements SortStrategyFactory {

    @Override
    public List<SortStrategy> getSortStrategies(SortProperties sortProperties) {
        return sortProperties.getSortProperties().stream()
                .map(this::getSortStrategy)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public SortStrategy getSortStrategy(SortProperty sortProperty) {
        SortableField sortableField = sortProperty.getSortableField();
        SortDirection sortDirection = sortProperty.getSortDirection();

        if (sortableField != null && sortDirection != null) {
            switch (sortableField) {
                case URL:
                    return new UrlStrategy(sortDirection);
                case DEFAULT:
                case LABEL:
                    return new LabelStrategy(sortDirection);
            }
        }

        return null;
    }
}
