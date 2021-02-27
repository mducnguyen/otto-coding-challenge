package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;

import java.util.List;

public interface SortStrategyFactory {
    /**
     * Generate a {@link List} of {@link SortStrategy} with the given {@link SortProperties}
     *
     * @param sortProperties {@link SortProperties}
     * @return {@link List} of {@link SortStrategy}
     */
    List<SortStrategy> getSortStrategies(SortProperties sortProperties);
}
