package de.duc.nguyen.occ.catalogfilter.it;

import de.duc.nguyen.occ.catalogfilter.CatalogFilterApplication;
import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;
import de.duc.nguyen.occ.catalogfilter.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinks_thenReturnAnArrayOfLinks() throws Exception {

        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(net.minidev.json.JSONArray.class))
        );
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinksWithRequestParameterParent_thenReturnAnArrayOfLinks() throws Exception {
        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links?parent=Alter")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(net.minidev.json.JSONArray.class)));
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinksWithRequestParameterSortWithFullOption_thenReturnAnArrayOfLinks() throws Exception {
        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links?parent=Alter&sort=label:asc,url:desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(net.minidev.json.JSONArray.class)));
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinksWithRequestParameterSortWithOneSortOption_thenReturnAnArrayOfLinks() throws Exception {
        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links?parent=Alter&sort=label:desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(net.minidev.json.JSONArray.class)));
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinksWithRequestParameterSortWithMissingDirection_thenReturnAnArrayOfLinks() throws Exception {
        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links?parent=Alter&sort=label,url")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(net.minidev.json.JSONArray.class)));
    }

    @Test
    public void givenTheApiWorkAsExpected_whenGetLinksWithRequestParameterSortWithInvalidField_thenReturnAnArrayOfLinks() throws Exception {
        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links?parent=Alter&sort=abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(Map.class)));
    }


    @Test
    public void givenTheParentCannotBefound_whenGetLinksWithRequestParameterSort_thenReturnNo_ContentMessage() throws Exception {

        ResponseEntity<Catalog> responseEntity = ResponseEntity.ok(TestUtils.getTestCatalog());
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        mockMvc.perform(get("/links?parent=abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }
}
