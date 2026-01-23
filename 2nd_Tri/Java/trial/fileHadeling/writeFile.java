public class writeFile {
    public static void main(String[] args) throws Exception {
    // overwrite file
    Files.writeString(Path.of("output.txt"), "First line\n");

    // append new lines
    Files.writeString(Path.of("output.txt"), "Second line\n", StandardOpenOption.APPEND);
    Files.writeString(Path.of("output.txt"), "Third line\n", StandardOpenOption.APPEND);
}

}
