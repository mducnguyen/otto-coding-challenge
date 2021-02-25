package de.duc.nguyen.occ.catalogfilter.models;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog implements Serializable {
    private List<AbstractNode> navigationEntries;

    public void setParentForNodes() {
        navigationEntries.forEach(AbstractNode::setParentForChildren);
    }

    public List<Link> getLinks() {
        return navigationEntries.stream()
                .flatMap(section -> section.getLeaves().stream().map(leaf -> (Link) leaf))
                .collect(Collectors.toList());
    }
}
