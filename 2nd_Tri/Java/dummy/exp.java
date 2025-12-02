// import java.io.*;
// import java.nio.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;


// public class exp {
//     public static void main(String[] args) {
//         String data = "This is a sample text for file operations.";

//         // Writing to a file
//         try (FileOutputStream fos = new FileOutputStream("sample.txt")) {
//             byte[] bytes = data.getBytes();
//             fos.write(bytes);
//             System.out.println("Data written to file successfully.");
//         } catch (IOException e) {
//             System.out.println("Error writing to file: " + e.getMessage());
//         }

//         // Reading from a file
//         try (FileInputStream fis = new FileInputStream("sample.txt")) {
//             byte[] buffer = new byte[1024];
//             int bytesRead = fis.read(buffer);
//             String fileData = new String(buffer, 0, bytesRead);
//             System.out.println("Data read from file: " + fileData);
//         } catch (IOException e) {
//             System.out.println("Error reading from file: " + e.getMessage());
//         }
//     }
// }

public class exp{
    public static void main(String[] args){
        System.out.println("Check if file exists and read content");
        try {
            // read file method
            readFile("./samplei.txt");
            // String readFile = Files.readAllLines(Path.of("./sample.txt"));
            // Print file content
            // for (String line : readFile) {
            //     System.out.println(line);
            // }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void readFile(String filePath) throws IOException {
        // FileInputStream fis = new FileInputStream(filePath);

        Path path = Path.of(filePath);
        // byte[] fileBytes = Files.readAllBytes(path);

        if(!Files.exists(path)){
            throw new FileNotFoundException("File not found at path: " + filePath);
        }
        FileInputStream fis = new FileInputStream(filePath);
        // byte[] buffer = new byte[1024];
        // int bytesRead = fis.read(buffer);
        // String fileData = new String(buffer, 0, bytesRead);
        // System.out.println("Data read from file: " + fileData);
        // fis.close();

        System.out.println("File exists at path: " + filePath);
        int data;
        while ((data = fis.read()) != -1) {
            System.out.print((char) data);
        }
        fis.close();
    }
}