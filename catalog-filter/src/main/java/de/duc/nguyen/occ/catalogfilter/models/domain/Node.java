package de.duc.nguyen.occ.catalogfilter.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Node implements Serializable {
    private String label;
    private String url;
    private String type;
    private Node parent;
    private List<Node> children;

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    public void initParentForChildren() {
        if (hasChildren())
            this.children.forEach(children -> {
                children.setParent(this);
                children.initParentForChildren();
            });
    }

    public void setParentForChildren(Node parent) {
        if (children != null)
            this.children.forEach(children -> children.setParent(parent));
    }

    public List<Node> getAllLeaves() {

        if (this.isLeaf()) {
            return Collections.singletonList(this);
        }

        return children.stream()
                .flatMap(child -> child.getAllLeaves().stream())
                .collect(Collectors.toList());
    }

    public List<Node> findFirstToHaveLabel(String label) {

        if (getLabel().equalsIgnoreCase(label)) {
            return List.of(this);
        } else if (hasChildren()) {
            return children.stream()
                    .flatMap(child -> child.findFirstToHaveLabel(label).stream())
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
