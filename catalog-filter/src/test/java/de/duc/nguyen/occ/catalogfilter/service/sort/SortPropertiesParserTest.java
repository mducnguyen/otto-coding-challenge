package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortableField;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class SortPropertiesParserTest {

    @Test
    public void givenSingleOption_whenParseSortProperties_thenReturnSortPropertiesWithOneProperty() {
        // given
        String sortOption = "label";

        // when
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.LABEL);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DEFAULT);
    }


    @Test
    public void givenSingleOptionWithDirection_whenParseSortProperties_thenReturnSortProprtiesWithOneProperty() {
        // given
        String sortOption = "label:desc";

        // when
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.LABEL);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DESC);
    }

    @Test
    public void givenTwoOptionWithDirection_whenParseSortProperties_thenReturn2SortProprties() {
        // given
        String sortOption = "label:desc,url:asc";

        // when
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 2);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.LABEL);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DESC);
        assertEquals(sortProperties.getSortProperties().get(1).getSortableField(), SortableField.URL);
        assertEquals(sortProperties.getSortProperties().get(1).getSortDirection(), SortDirection.ASC);
    }

    @Test
    public void givenNoOption_whenParseSortProperties_thenReturn0SortProprties() {
        // given
        String sortOption = "";

        // when
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 0);
    }

    @Test
    public void givenSingleInvalidOption_whenParseSortProperties_thenReturnSortPropertiesWithOneProperty() {
        // given
        String sortOption = "name";

        // when
        SortProperties sortProperties = SortPropertiesParser.parseSortProperties(sortOption);

        // then
        assertEquals(sortProperties.getSortProperties().size(), 1);
        assertEquals(sortProperties.getSortProperties().get(0).getSortableField(), SortableField.DEFAULT);
        assertEquals(sortProperties.getSortProperties().get(0).getSortDirection(), SortDirection.DEFAULT);
    }



}