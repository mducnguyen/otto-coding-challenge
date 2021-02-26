package de.duc.nguyen.occ.catalogfilter.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Link extends AbstractNode {
    private String url;

    @Override
    public boolean isLeaf() {
        return true;
    }
}
