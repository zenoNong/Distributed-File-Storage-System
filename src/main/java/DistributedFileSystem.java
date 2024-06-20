package main.java;

import java.util.HashMap;
import java.util.Map;

public class DistributedFileSystem {
    private Map<String, Node> nodes;
    private Map<String, String[]> chunkLocations;

    public DistributedFileSystem() {
        nodes = new HashMap<>();
        chunkLocations = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    public void replicateChunk(Chunk chunk, int replicationFactor) {
        String[] locations = new String[replicationFactor];
        for (int i = 0; i < replicationFactor; i++) {
            Node node = getRandomNode();
            node.addChunk(chunk);
            locations[i] = node.getId();
        }
        chunkLocations.put(chunk.getId(), locations);
    }

    private Node getRandomNode() {
        // Logic to get a random node from nodes map
        // For simplicity, assume a method that returns a random node
        return nodes.values().iterator().next();
    }

    // Add this method
    public Map<String, Node> getNodes() {
        return nodes;
    }
}
