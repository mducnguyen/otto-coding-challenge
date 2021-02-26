package de.duc.nguyen.occ.catalogfilter.service.sort;

import com.google.common.collect.ComparisonChain;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDTO;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;

public interface SortStrategyFactory {
    ComparisonChain getSortStrategy(SortProperties sortProperties, LinkDTO link1, LinkDTO link2);
}
