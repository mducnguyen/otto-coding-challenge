package de.duc.nguyen.occ.catalogfilter.models.domain;

public class Node extends AbstractNode {
    @Override
    public boolean isLeaf() {
        return false;
    }
}
