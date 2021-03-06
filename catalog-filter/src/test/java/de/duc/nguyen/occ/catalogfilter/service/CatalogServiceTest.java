package de.duc.nguyen.occ.catalogfilter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.api.CatalogApi;
import de.duc.nguyen.occ.catalogfilter.models.domain.Node;
import de.duc.nguyen.occ.catalogfilter.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatalogServiceTest {

    @Mock
    CatalogApi catalogApi;

    CatalogService catalogService;

    @Before
    public void init() {
        catalogService = new CatalogServiceImpl(catalogApi);
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinks_thenReturnAListOfLinks() throws JsonProcessingException {
        when(catalogApi.getCatalog()).thenReturn(TestUtils.getTestCatalog());

        List<Node> links = catalogService.getLinks();

        assertThat(links.isEmpty(), is(false));
        assertThat(links, hasSize(5));
    }


    @Test
    public void givenTheApiWorkAsExpected_whenGetLinksWithParentFilter_thenReturnAListOfLinks() throws JsonProcessingException {

        when(catalogApi.getCatalog()).thenReturn(TestUtils.getTestCatalog());
        String parent = "Baby & Kleinkind";

        List<Node> links = catalogService.getLinks(parent);

        assertThat(links.isEmpty(), is(false));
        assertThat(links, hasSize(3));
    }
}