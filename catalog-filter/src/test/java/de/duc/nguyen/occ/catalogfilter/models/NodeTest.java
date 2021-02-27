package de.duc.nguyen.occ.catalogfilter.models;

import de.duc.nguyen.occ.catalogfilter.models.domain.Node;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeTest {

    @Test
    public void whenGetAllLeaves_thenReturnAllLeaves() {

        Node leaf1 = Node.builder().label("leaf1").url("http://leaf1.com").build();
        Node leaf2 = Node.builder().label("leaf2").url("http://leaf2.com").build();
        Node leaf3 = Node.builder().label("leaf3").url("http://leaf3.com").build();
        Node nodeA = Node.builder().label("nodeA")
                .children(List.of(leaf1, leaf2))
                .build();
        Node section = Node.builder().label("section")
                .children(List.of(nodeA, leaf3))
                .build();

        section.initParentForChildren();

        List<Node> allLeaves = section.getAllLeaves();

        assertThat(allLeaves.size(), is(3));
        assertThat(allLeaves.stream().allMatch(Node::isLeaf), is(true));
    }

    @Test
    public void whenFindFirstChildrenToHaveLabel_thenReturnNode() {
        Node leaf1 = Node.builder().label("leaf1").url("http://leaf1.com").build();
        Node leaf2 = Node.builder().label("leaf2").url("http://leaf2.com").build();
        Node leaf3 = Node.builder().label("leaf3").url("http://leaf3.com").build();
        Node nodeA1 = Node.builder().label("nodeA")
                .children(List.of(leaf1, leaf2))
                .build();
        Node nodeA2 = Node.builder().label("nodeA")
                .children(List.of(leaf3))
                .build();
        Node section = Node.builder().label("section")
                .children(List.of(nodeA1, nodeA2))
                .build();

        section.initParentForChildren();

        List<Node> nodesWithParentNodeA = section.findFirstChildrenToHaveLabel("nodeA");

        assertThat(nodesWithParentNodeA.size(), is(2));
        assertThat(nodesWithParentNodeA.stream().allMatch(node -> node.getLabel().equals("nodeA")), is(true));
    }
}