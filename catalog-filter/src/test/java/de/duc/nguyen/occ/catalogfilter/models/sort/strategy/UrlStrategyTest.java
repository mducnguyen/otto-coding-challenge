package de.duc.nguyen.occ.catalogfilter.models.sort.strategy;

import com.google.common.collect.ComparisonChain;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;
import de.duc.nguyen.occ.catalogfilter.utils.TestUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UrlStrategyTest {

    @Test
    public void givenSortDirectionIsAsc_whenChain_ReturnChainASC() {

        SortStrategy sortStrategy = new UrlStrategy(SortDirection.ASC);

        ComparisonChain comparisonChain = ComparisonChain.start();

        LinkDto link1 = new LinkDto();
        link1.setLabel("abc");
        link1.setUrl("123");

        LinkDto link2 = new LinkDto();
        link2.setLabel("edf");
        link2.setUrl("456");

        LinkDto link3 = new LinkDto();
        link3.setLabel("jqk");
        link3.setUrl("789");

        List<LinkDto> links = new ArrayList<>(List.of(link3, link2, link1));

        links.sort((l1, l2) -> sortStrategy.chain(comparisonChain, l1, l2).result());

        assertTrue(TestUtils.istSortedUrlAsc(links));

    }
}