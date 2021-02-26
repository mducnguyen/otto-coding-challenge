package de.duc.nguyen.occ.catalogfilter.models.sort.strategy;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;

public class UrlStrategy extends SortStrategy {

    public UrlStrategy(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public ComparisonChain chain(ComparisonChain comparisonChain, LinkDto link1, LinkDto link2) {
        ComparisonChain enrichedChain = comparisonChain;

        switch (sortDirection) {
            case ASC:
            case DEFAULT:
                enrichedChain = comparisonChain.compare(link1.getUrl(), link2.getUrl(), Ordering.natural().nullsLast());
                break;
            case DESC:
                enrichedChain = comparisonChain.compare(link1.getUrl(), link2.getUrl(), Ordering.from((o1, o2) -> o2.compareTo(o1)));
                break;
        }

        return enrichedChain;
    }
}
