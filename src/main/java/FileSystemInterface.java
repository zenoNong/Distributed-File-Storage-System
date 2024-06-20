package main.java;

import java.util.ArrayList;
import java.util.List;

public class FileSystemInterface {
    private DistributedFileSystem dfs;
    private MetadataManager metadataManager;

    public FileSystemInterface(DistributedFileSystem dfs) {
        this.dfs = dfs;
        this.metadataManager = new MetadataManager();
    }

    public void uploadFile(String filePath, String content) {
        // Split the file content into chunks
        byte[] contentBytes = content.getBytes();
        int chunkSize = 4; // Fixed chunk size for simplicity
        int numChunks = (int) Math.ceil(contentBytes.length / (double) chunkSize);

        List<String> chunkIds = new ArrayList<>();
        for (int i = 0; i < numChunks; i++) {
            int start = i * chunkSize;
            int end = Math.min(contentBytes.length, start + chunkSize);
            byte[] chunkData = new byte[end - start];
            System.arraycopy(contentBytes, start, chunkData, 0, end - start);
            Chunk chunk = new Chunk(chunkData);
            chunkIds.add(chunk.getId());

            // Replicate the chunk across nodes
            dfs.replicateChunk(chunk, 2); // Replicate each chunk to 2 nodes for fault tolerance
        }

        // Store the file metadata
        FileMetadata metadata = new FileMetadata(filePath, contentBytes.length, chunkIds.toArray(new String[0]));
        metadataManager.addMetadata(filePath, metadata);
        System.out.println("File uploaded successfully.");
    }

    public String downloadFile(String filePath) {
        // Retrieve the file metadata
        FileMetadata metadata = metadataManager.getMetadata(filePath);
        if (metadata == null) {
            System.out.println("File not found.");
            return null;
        }

        // Retrieve and reassemble the chunks
        byte[] contentBytes = new byte[(int) metadata.getFileSize()];
        String[] chunkIds = metadata.getChunkIds();
        int offset = 0;
        for (String chunkId : chunkIds) {
            Chunk chunk = null;
            for (Node node : dfs.getNodes().values()) {
                chunk = node.getChunk(chunkId);
                if (chunk != null) break;
            }
            if (chunk == null) {
                System.out.println("Chunk missing: " + chunkId);
                return null;
            }
            byte[] chunkData = chunk.getData();
            System.arraycopy(chunkData, 0, contentBytes, offset, chunkData.length);
            offset += chunkData.length;
        }

        System.out.println("File downloaded successfully.");
        return new String(contentBytes);
    }
}
