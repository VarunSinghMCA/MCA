import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class newFile {
    public static void main(String[] args) {
        try {
            readFile("./sample.txt");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void readFile(String dir) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(dir));

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
