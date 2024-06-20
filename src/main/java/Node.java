package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Node {
    private String id;
    private Map<String, Chunk> chunks;

    public Node() {
        this.id = UUID.randomUUID().toString();
        this.chunks = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void addChunk(Chunk chunk) {
        chunks.put(chunk.getId(), chunk);
    }

    public Chunk getChunk(String chunkId) {
        return chunks.get(chunkId);
    }
}
