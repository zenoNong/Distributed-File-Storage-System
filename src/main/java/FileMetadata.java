package main.java;

public class FileMetadata {
    private String filePath;
    private long fileSize;
    private String[] chunkIds;

    public FileMetadata(String filePath, long fileSize, String[] chunkIds) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.chunkIds = chunkIds;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String[] getChunkIds() {
        return chunkIds;
    }
}

