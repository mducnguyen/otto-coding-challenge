package de.duc.nguyen.occ.catalogfilter.it;

import de.duc.nguyen.occ.catalogfilter.CatalogFilterApplication;
import de.duc.nguyen.occ.catalogfilter.api.CatalogApi;
import de.duc.nguyen.occ.catalogfilter.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {CatalogFilterApplication.class}
)
@ActiveProfiles("integration-test")
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureMockMvc
public class LinksEndpointITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CatalogApi catalogApi;

    @Before
    public void setUp() {
        when(catalogApi.getCatalog()).thenReturn(TestUtils.getTestCatalogJson());
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinks_thenReturnAnArrayOfLinks() throws Exception {

        mockMvc.perform(get("/links")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(net.minidev.json.JSONArray.class))
        );
    }
}
