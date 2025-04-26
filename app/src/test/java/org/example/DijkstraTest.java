package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class DijkstraTest {

    @Test
    public void testCompleteGraphShortestPaths() {
        // Setup the same graph as in App.main()
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeZ = new Node<>("Z");

        nodeA.addAdjacentNode(nodeB, 3);
        nodeA.addAdjacentNode(nodeC, 4);
        nodeA.addAdjacentNode(nodeD, 2);

        nodeB.addAdjacentNode(nodeE, 5);

        nodeC.addAdjacentNode(nodeF, 3);

        nodeD.addAdjacentNode(nodeZ, 10);
        nodeD.addAdjacentNode(nodeC, 1);
        nodeE.addAdjacentNode(nodeZ, 8);

        nodeF.addAdjacentNode(nodeZ, 4);

        Dijkstra<String> dijkstra = new Dijkstra<>();
        dijkstra.calculateShortestPath(nodeA);

        // Verify some key shortest paths
        assertEquals(0, nodeA.getDistance());
        assertEquals(3, nodeB.getDistance());
        assertEquals(3, nodeC.getDistance()); // A->D->C (2+1) is shorter than A->C (4)
        assertEquals(2, nodeD.getDistance());
        assertEquals(8, nodeE.getDistance()); // A->B->E (3+5)
        assertEquals(6, nodeF.getDistance()); // A->D->C->F (2+1+3)
        assertEquals(10, nodeZ.getDistance()); // A->D->C->F->Z (2+1+3+4)

        // Verify the path to Z
        List<Node<String>> pathToZ = nodeZ.getShortestPath();
        assertEquals(4, pathToZ.size());
        assertEquals(nodeA, pathToZ.get(0));
        assertEquals(nodeD, pathToZ.get(1));
        assertEquals(nodeC, pathToZ.get(2));
        assertEquals(nodeF, pathToZ.get(3));
    }
}