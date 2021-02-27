package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortInvalidPropertyException;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortableField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortPropertiesParserImplTest {

    SortPropertiesParser sortPropertiesParser;


    @Before
    public void setUp() {
        sortPropertiesParser = new SortPropertiesParserImpl();
    }

    @Test
    public void givenSingleOption_whenParseSortProperties_thenReturnSortPropertiesWithOneProperty() throws SortInvalidPropertyException {
        // given
        String sortOption = "label";

        // when
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.LABEL);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DEFAULT);
    }


    @Test
    public void givenSingleOptionWithDirection_whenParseSortProperties_thenReturnSortProprtiesWithOneProperty() throws SortInvalidPropertyException {
        // given
        String sortOption = "label:desc";

        // when
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.LABEL);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DESC);
    }

    @Test
    public void givenTwoOptionWithDirection_whenParseSortProperties_thenReturn2SortProprties() throws SortInvalidPropertyException {
        // given
        String sortOption = "label:desc,url:asc";

        // when
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 2);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.LABEL);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DESC);
        assertEquals(sortProperties.getSortProperties().get(1).getSortableField(), SortableField.URL);
        assertEquals(sortProperties.getSortProperties().get(1).getSortDirection(), SortDirection.ASC);
    }

    @Test
    public void givenNoOption_whenParseSortProperties_thenReturn0SortProprties() throws SortInvalidPropertyException {
        // given
        String sortOption = "";

        // when
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 0);
    }

    @Test
    public void givenInvalidOption_whenParseSortProperties_thenReturn0SortProprties() throws SortInvalidPropertyException {
        // given
        String sortOption = ":";

        // when
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
    }

    @Test
    public void givenSingleInvalidOption_whenParseSortProperties_thenReturnSortPropertiesWithOneProperty() throws SortInvalidPropertyException {
        // given
        String sortOption = ":";

        // when
        SortProperties sortProperties = sortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.DEFAULT);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DEFAULT);
    }


    @Test(expected = SortInvalidPropertyException.class)
    public void givenSingleInvalidOption_whenParseSortProperties_thenThrowException() throws SortInvalidPropertyException {
        // given
        String sortOption = "name";

        // when
        sortPropertiesParser.parseSortProperties(sortOption);

    }


}