package de.duc.nguyen.occ.catalogfilter.service.sort;

import com.google.common.collect.ComparisonChain;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;

public interface SortStrategyFactory {
    ComparisonChain getSortStrategy(SortProperties sortProperties, LinkDto link1, LinkDto link2);
}
