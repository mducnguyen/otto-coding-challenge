package de.duc.nguyen.occ.catalogfilter.models.sort;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SortProperty {
    private SortableField sortableField;
    private SortDirection sortDirection;
}
