package de.duc.nguyen.occ.catalogfilter.mapper;

import de.duc.nguyen.occ.catalogfilter.models.Link;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDto;

public class DtoMapper {
    public static LinkDto toLinkDto(Link link) {
        return LinkDto.builder()
                .url(link.getUrl())
                .label(link.getLabel())
                .build();
    }
}
