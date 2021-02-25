package de.duc.nguyen.occ.catalogfilter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Link extends AbstractNode {
    private String url;

    @Override
    public boolean isLeaf() {
        return true;
    }
}
