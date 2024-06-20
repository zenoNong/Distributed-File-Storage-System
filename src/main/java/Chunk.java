package main.java;

import java.util.UUID;

public class Chunk {
    private String id;
    private byte[] data;

    public Chunk(byte[] data) {
        this.id = UUID.randomUUID().toString();
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }
}
