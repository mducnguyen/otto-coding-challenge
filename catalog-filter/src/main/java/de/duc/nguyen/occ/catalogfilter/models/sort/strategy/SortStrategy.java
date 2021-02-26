package de.duc.nguyen.occ.catalogfilter.models.sort.strategy;

import com.google.common.collect.ComparisonChain;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperty;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;

public abstract class SortStrategy {

    protected SortDirection sortDirection;

    public abstract ComparisonChain chain(ComparisonChain comparisonChain, LinkDto link1, LinkDto link2);

}
