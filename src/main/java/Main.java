package main.java;

public class Main {
    public static void main(String[] args) {
        // Initialize the distributed file system
        DistributedFileSystem dfs = new DistributedFileSystem();
        
        // Create and add nodes to the system
        for (int i = 0; i < 3; i++) {
            dfs.addNode(new Node());
        }

        // Create the interface
        FileSystemInterface fileSystemInterface = new FileSystemInterface(dfs);

        // Simulate user uploading and downloading files
        fileSystemInterface.uploadFile("example.txt", "Hello, this is a test file.");
        String downloadedContent = fileSystemInterface.downloadFile("example.txt");

        // Display the downloaded content
        System.out.println("Downloaded File Content: " + downloadedContent);
    }
}
