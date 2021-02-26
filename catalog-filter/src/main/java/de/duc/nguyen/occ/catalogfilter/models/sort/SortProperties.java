package de.duc.nguyen.occ.catalogfilter.models.sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class SortProperties {
    private List<SortProperty> sortProperties;
}
