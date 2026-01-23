
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WriteExample {
    public static void main(String[] args) {
        try {
            writeFile("./output.txt", "Hello World!\n");
            appendToFile("./output.txt", "This line was appended.\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error 404: File Not Found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void writeFile(String dir, String content) throws IOException {
        Files.writeString(Path.of(dir), content);   // overwrites or creates
    }

    public static void appendToFile(String dir, String content) throws IOException {
        Files.writeString(
            Path.of(dir),
            content,
            StandardOpenOption.CREATE, // creates file if not exists
            StandardOpenOption.APPEND
        );
    }
}
