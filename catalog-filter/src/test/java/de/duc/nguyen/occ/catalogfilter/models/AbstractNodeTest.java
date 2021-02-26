package de.duc.nguyen.occ.catalogfilter.models;

import de.duc.nguyen.occ.catalogfilter.models.domain.AbstractNode;
import de.duc.nguyen.occ.catalogfilter.models.domain.Node;
import org.junit.Before;
import org.junit.Test;

public class AbstractNodeTest {

    AbstractNode node;

    @Before
    public void setUp() throws Exception {
        node = new Node();
    }

    @Test
    public void getAllLeaves() {
    }

    @Test
    public void findFirstChildrenToHaveLabel() {
    }
}