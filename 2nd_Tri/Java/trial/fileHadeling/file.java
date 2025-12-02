import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class file{
    public static void main(String[] args){
        try{
            readFile("./sample.txt");
        } catch (FileNotFoundException e){
            System.out.println("Error 404: File Not Found: " + e.getMessage());
        } catch (IOException e){
            System.out.println("IO Error while reading file: " + e.getMessage());
        } catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void readFile(String dir) throws IOException{
        Path path = Path.of(dir);

        if (!Files.exists(path)){
            throw new FileNotFoundException(dir + " does not exist.");
        }
        FileInputStream fis = new FileInputStream(dir);
        System.out.println("File opened successfully: " + dir);

        int data;
        while((data = fis.read()) != -1){
            System.out.print((char)data);
        }
        fis.close();
    }
}