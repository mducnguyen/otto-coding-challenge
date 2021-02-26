package de.duc.nguyen.occ.catalogfilter.service.sort;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDto;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperty;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortableField;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SortStrategyFactoryImpl implements SortStrategyFactory {

    public ComparisonChain getSortStrategy(SortProperties sortProperties, LinkDto link1, LinkDto link2) {
        List<SortProperty> properties = sortProperties.getSortProperties();

        ComparisonChain comparisonChain = ComparisonChain.start();

        for (SortProperty property : properties) {
            comparisonChain = getComparisonChain(comparisonChain, property, link1, link2);
        }

        return comparisonChain;
    }


    private ComparisonChain getComparisonChain(ComparisonChain comparisonChain, SortProperty sortProperty, LinkDto link1, LinkDto link2) {

        SortableField sortableField = sortProperty.getSortableField();
        SortDirection sortDirection = sortProperty.getSortDirection();

        if (sortableField != null && sortDirection != null) {
            switch (sortableField) {
                case URL: {
                    switch (sortDirection) {
                        case ASC:
                        case DEFAULT:
                            comparisonChain = comparisonChain.compare(link1.getUrl(), link2.getUrl(), Ordering.natural().nullsLast());
                            break;
                        case DESC:
                            comparisonChain = comparisonChain.compare(link1.getUrl(), link2.getUrl(), Ordering.from((o1, o2) -> o2.compareTo(o1)));
                            break;
                    }
                }
                break;
                case LABEL: {
                    switch (sortDirection) {
                        case ASC:
                        case DEFAULT:
                            comparisonChain = comparisonChain.compare(link1.getLabel(), link2.getLabel(), Ordering.natural().nullsLast());
                            break;
                        case DESC:
                            comparisonChain = comparisonChain.compare(link1.getLabel(), link2.getLabel(), Ordering.from((o1, o2) -> o2.compareTo(o1)));
                            break;
                    }
                }
                break;
                case DEFAULT:
                    comparisonChain = comparisonChain.compare(link1.getLabel(), link2.getLabel(), Ordering.natural().nullsLast());
                    break;
            }
        }

        return comparisonChain;
    }
}
