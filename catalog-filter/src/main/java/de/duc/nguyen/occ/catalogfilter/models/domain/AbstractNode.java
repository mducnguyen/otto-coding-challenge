package de.duc.nguyen.occ.catalogfilter.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Node.class, name = "node"),
        @JsonSubTypes.Type(value = Node.class, name = "section"),
        @JsonSubTypes.Type(value = Link.class, name = "external-link"),
        @JsonSubTypes.Type(value = Link.class, name = "link")

})
@Getter
@Setter
public abstract class AbstractNode implements Serializable {
    private String label;

    @JsonIgnore
    private AbstractNode parent;
    private List<AbstractNode> children;

    public void initParentForChildren() {
        if (children != null)
            this.children.forEach(children -> {
                children.setParent(this);
                children.initParentForChildren();
            });
    }

    public void setParentForChildren(AbstractNode parent) {
        if (children != null)
            this.children.forEach(children -> children.setParent(parent));
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public List<AbstractNode> getAllLeaves() {

        if (this.isLeaf()) {
            return Collections.singletonList(this);
        }

        return children.stream()
                .flatMap(child -> child.getAllLeaves().stream())
                .collect(Collectors.toList());
    }

    public List<AbstractNode> findFirstChildrenToHaveLabel(String label) {
        if (hasChildren()) {
            return children.stream().flatMap(child -> {
                if (child.getLabel().equalsIgnoreCase(label)) {
                    return Stream.of(child);
                } else {
                    return child.findFirstChildrenToHaveLabel(label).stream();
                }
            }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    abstract public boolean isLeaf();
}
