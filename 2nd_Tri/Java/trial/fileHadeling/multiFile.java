import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class multiFile {
    public static void main(String[] args) {
        System.out.println("This is a placeholder for multiFile class.");
    }
    
    public static void readfile(String dir) throws IOException{
        Path path = Path.of(dir);

        if(!Files.exists(path)){
            throw new FileNotFoundException(dir + " does not exist.");
        }
    }
}
