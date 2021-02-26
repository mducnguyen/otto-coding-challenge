package de.duc.nguyen.occ.catalogfilter.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkDTO {
    private String label;
    private String url;
}
