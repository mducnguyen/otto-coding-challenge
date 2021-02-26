package de.duc.nguyen.occ.catalogfilter.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog implements Serializable {

    private List<AbstractNode> navigationEntries = new ArrayList<>();

    public void initParentForNodes() {
        navigationEntries.forEach(AbstractNode::initParentForChildren);
    }

}
