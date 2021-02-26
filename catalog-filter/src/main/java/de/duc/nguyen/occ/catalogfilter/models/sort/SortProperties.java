package de.duc.nguyen.occ.catalogfilter.models.sort;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class SortProperties {
    private List<SortProperty> sortProperties;
}
