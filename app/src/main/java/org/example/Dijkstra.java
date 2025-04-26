package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra<T> {
    public void calculateShortestPath(Node<T> source) {
        source.setDistance(0);
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node<T> currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes().forEach((key, value) -> {
                evaluateDistanceAndPath(key, value, currentNode);
                unsettledNodes.add(key);
            });
        }
    }

    private void evaluateDistanceAndPath(Node<T> adjacentNode, Integer edgeWeight, Node<T> sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList());
        }
    }

    public void printPaths(List<Node<T>> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getName).map(Objects::toString)
                    .collect(Collectors.joining(" -> "));
            System.out.println((path.isBlank())
                    ? "%s : %s".formatted(node.getName(), node.getDistance())
                    : "%s -> %s : %s".formatted(path, node.getName(), node.getDistance()));
        });
    }

}
