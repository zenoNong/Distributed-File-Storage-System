package main.java;
// src/main/java/MetadataManager.java
import java.util.HashMap;
import java.util.Map;

public class MetadataManager {
    private Map<String, FileMetadata> metadataStore;

    public MetadataManager() {
        metadataStore = new HashMap<>();
    }

    public void addMetadata(String filePath, FileMetadata metadata) {
        metadataStore.put(filePath, metadata);
    }

    public FileMetadata getMetadata(String filePath) {
        return metadataStore.get(filePath);
    }
}
