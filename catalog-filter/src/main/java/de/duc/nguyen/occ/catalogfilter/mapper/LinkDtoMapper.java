package de.duc.nguyen.occ.catalogfilter.mapper;

import de.duc.nguyen.occ.catalogfilter.models.AbstractNode;
import de.duc.nguyen.occ.catalogfilter.models.Link;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDto;

public class LinkDtoMapper {
    public static LinkDto toLinkDto(Link link) {
        return LinkDto.builder()
                .url(link.getUrl())
                .label(getLabelOfLink(link))
                .build();
    }

    private static String getLabelOfLink(Link link) {
        StringBuilder labelBuilder = new StringBuilder();
        labelBuilder.append(link.getLabel());

        AbstractNode parent = link;

        while (parent.hasParent()) {
            parent = parent.getParent();
            labelBuilder.insert(0, parent.getLabel() + " - ");
        }

        return labelBuilder.toString();
    }
}
