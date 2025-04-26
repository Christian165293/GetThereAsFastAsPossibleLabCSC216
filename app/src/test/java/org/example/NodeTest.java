package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class NodeTest {

    @Test
    public void testNodeCreation() {
        Node<String> node = new Node<>("Test");
        assertEquals("Test", node.getName());
        assertEquals(Integer.MAX_VALUE, node.getDistance());
        assertTrue(node.getShortestPath().isEmpty());
        assertTrue(node.getAdjacentNodes().isEmpty());
    }

    @Test
    public void testAddAdjacentNode() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");

        nodeA.addAdjacentNode(nodeB, 5);

        assertEquals(1, nodeA.getAdjacentNodes().size());
        assertTrue(nodeA.getAdjacentNodes().containsKey(nodeB));
        assertEquals(5, nodeA.getAdjacentNodes().get(nodeB));
    }

    @Test
    public void testCompareTo() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");

        nodeA.setDistance(5);
        nodeB.setDistance(10);

        assertTrue(nodeA.compareTo(nodeB) < 0);
        assertTrue(nodeB.compareTo(nodeA) > 0);
        assertEquals(0, nodeA.compareTo(nodeA));
    }

    @Test
    public void testSetShortestPath() {
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");

        nodeA.setShortestPath(List.of(nodeB, nodeC));

        assertEquals(2, nodeA.getShortestPath().size());
        assertEquals(nodeB, nodeA.getShortestPath().get(0));
        assertEquals(nodeC, nodeA.getShortestPath().get(1));
    }
}