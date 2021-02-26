package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperty;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortableField;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.LabelStrategy;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.UrlStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortStrategyFactoryTest {
    
    SortStrategyFactory sortStrategyFactory;

    @Before
    public void setUp() {
        sortStrategyFactory = new SortStrategyFactoryImpl();
    }

    @Test
    public void givenURlSortProperties_whenGetSortStrategies_thenReturnUrlStrategies() {
        SortProperty sortPropertyUrlDesc = SortProperty.builder()
                .sortableField(SortableField.URL)
                .sortDirection(SortDirection.DESC)
                .build();

        SortProperties sortProperties = SortProperties.builder()
                .sortProperties(List.of(sortPropertyUrlDesc))
                .build();


        List<SortStrategy> sortStrategies = sortStrategyFactory.getSortStrategies(sortProperties);

        assertEquals(sortStrategies.size(), 1);
        SortStrategy sortStrategy = sortStrategies.get(0);
        assertTrue(sortStrategy instanceof UrlStrategy);
    }

    @Test
    public void givenLabelSortProperties_whenGetSortStrategies_thenReturnLabelStrategies() {
        SortProperty sortPropertyUrlDesc = SortProperty.builder()
                .sortableField(SortableField.LABEL)
                .sortDirection(SortDirection.ASC)
                .build();

        SortProperties sortProperties = SortProperties.builder()
                .sortProperties(List.of(sortPropertyUrlDesc))
                .build();


        List<SortStrategy> sortStrategies = sortStrategyFactory.getSortStrategies(sortProperties);

        assertEquals(sortStrategies.size(), 1);
        SortStrategy sortStrategy = sortStrategies.get(0);
        assertTrue(sortStrategy instanceof LabelStrategy);
    }

    @Test
    public void givenInvalidSortProperties_whenGetSortStrategies_thenReturnLabelStrategies() {
        SortProperty sortPropertyUrlDesc = SortProperty.builder()
                .build();

        SortProperties sortProperties = SortProperties.builder()
                .sortProperties(List.of(sortPropertyUrlDesc))
                .build();


        List<SortStrategy> sortStrategies = sortStrategyFactory.getSortStrategies(sortProperties);

        assertEquals(sortStrategies.size(), 0);
    }


}