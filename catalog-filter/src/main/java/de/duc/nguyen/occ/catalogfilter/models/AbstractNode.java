package de.duc.nguyen.occ.catalogfilter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Node.class, name = "node"),
        @JsonSubTypes.Type(value = Section.class, name = "section"),
        @JsonSubTypes.Type(value = ExternalLink.class, name = "external-link"),
        @JsonSubTypes.Type(value = Link.class, name = "link")

})
@Getter
@Setter
public abstract class AbstractNode implements Serializable {
    private String label;

    @JsonIgnore
    private AbstractNode parent;
    private List<AbstractNode> children;

    public void setParentForChildren() {
        if (children != null)
            this.children.forEach(children -> {
                children.setParent(this);
                children.setParentForChildren();
            });
    }

    abstract public boolean isLeaf();

    public List<AbstractNode> getLeaves() {

        if (this.isLeaf()) {
            return Collections.singletonList(this);
        }

        return children.stream()
                .flatMap(child -> child.getLeaves().stream())
                .collect(Collectors.toList());
    }
}
