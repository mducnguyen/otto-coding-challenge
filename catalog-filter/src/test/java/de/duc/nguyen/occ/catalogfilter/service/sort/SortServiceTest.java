package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.LabelStrategy;
import de.duc.nguyen.occ.catalogfilter.models.sort.strategy.SortStrategy;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;
import de.duc.nguyen.occ.catalogfilter.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SortServiceTest {

    @Mock
    SortStrategyFactory sortStrategyFactory;

    SortService sortService;

    @Before
    public void setUp() {
        sortService = new SortServiceImpl(sortStrategyFactory);
    }

    @Test
    public void whenSort_thenExpectSorted() {

        LinkDto link1 = new LinkDto();
        link1.setLabel("abc");
        link1.setUrl("123");

        LinkDto link2 = new LinkDto();
        link2.setLabel("edf");
        link2.setUrl("456");

        LinkDto link3 = new LinkDto();
        link3.setLabel("jqk");
        link3.setUrl("789");

        List<LinkDto> links = new ArrayList<>(List.of(link1, link2, link3));

        SortStrategy sortStrategy = new LabelStrategy(SortDirection.DESC);

        when(sortStrategyFactory.getSortStrategies(any(SortProperties.class))).thenReturn(List.of(sortStrategy));

        sortService.sort(links, "label:desc");

        assertTrue(TestUtils.istSortedLabelDesc(links));


    }


}