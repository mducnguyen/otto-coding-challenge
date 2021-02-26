package de.duc.nguyen.occ.catalogfilter.mapper;

import de.duc.nguyen.occ.catalogfilter.models.AbstractNode;
import de.duc.nguyen.occ.catalogfilter.models.Link;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;

public class LinkDtoMapper {
    public static LinkDto toLinkDto(Link link) {
        LinkDto linkDto = new LinkDto();
        linkDto.setLabel(getLabelOfLink(link));
        linkDto.setUrl(link.getUrl());
        return linkDto;
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
