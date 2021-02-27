package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SortPropertiesParserImpl implements SortPropertiesParser {

    public SortProperties parseSortProperties(String sortOptions) throws SortInvalidPropertyException {

        if (sortOptions == null || sortOptions.isEmpty()) {
            return SortProperties.builder().sortProperties(Collections.emptyList()).build();
        }

        List<String> options = Arrays.stream(sortOptions.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        List<SortProperty> sortProperties = new ArrayList<>();
        List<String> notNullOptions = options.stream()
                .filter(Objects::nonNull).collect(Collectors.toList());

        for (String option: notNullOptions) {
            SortProperty sortProperty;
            String[] property = option.trim().split(":");
            if (property.length > 1) {
                sortProperty = SortProperty.builder()
                        .sortableField(SortableField.fromString(property[0]))
                        .sortDirection(SortDirection.fromString(property[1]))
                        .build();
            } else if (property.length == 1) {
                sortProperty = SortProperty.builder()
                        .sortableField(SortableField.fromString(property[0]))
                        .sortDirection(SortDirection.DEFAULT)
                        .build();
            } else {
                sortProperty = SortProperty.builder()
                        .sortableField(SortableField.DEFAULT)
                        .sortDirection(SortDirection.DEFAULT)
                        .build();
            }

            sortProperties.add(sortProperty);
        }

        return SortProperties.builder().sortProperties(sortProperties).build();
    }
}
