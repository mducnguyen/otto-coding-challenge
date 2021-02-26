package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;

import java.util.List;

public interface SortStrategyFactory {
    List<SortStrategy> getSortStrategies(SortProperties sortProperties);
}
