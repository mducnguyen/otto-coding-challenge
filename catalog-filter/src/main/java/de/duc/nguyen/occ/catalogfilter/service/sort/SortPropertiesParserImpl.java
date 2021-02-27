package de.duc.nguyen.occ.catalogfilter.service.sort;

import de.duc.nguyen.occ.catalogfilter.models.sort.SortDirection;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperties;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortProperty;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortableField;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SortPropertiesParserImpl implements SortPropertiesParser {

    public SortProperties parseSortProperties(String sortOptions) {

        if (sortOptions == null || sortOptions.isEmpty()) {
            return SortProperties.builder().sortProperties(Collections.emptyList()).build();
        }

        List<String> options = Arrays.stream(sortOptions.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        List<SortProperty> sortProperties = options.stream()
                .filter(Objects::nonNull)
                .map(option -> {
                    String[] property = option.trim().split(":");
                    if (property.length > 1) {
                        return SortProperty.builder()
                                .sortableField(SortableField.fromString(property[0]))
                                .sortDirection(SortDirection.fromString(property[1]))
                                .build();
                    } else if (property.length == 1) {
                        return SortProperty.builder()
                                .sortableField(SortableField.fromString(property[0]))
                                .sortDirection(SortDirection.DEFAULT)
                                .build();
                    } else {
                        return SortProperty.builder()
                                .sortableField(SortableField.DEFAULT)
                                .sortDirection(SortDirection.DEFAULT)
                                .build();
                    }
                })
                .collect(Collectors.toList());

        return SortProperties.builder().sortProperties(sortProperties).build();
    }
}
