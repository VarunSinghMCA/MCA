// import java.io.*;
import java.util.*;



public class str {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String s = sc.nextLine();
            StringBuilder sb = new StringBuilder(s);
            sb.reverse();
            System.out.println(sb);
        }
    }
}
