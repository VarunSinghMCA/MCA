import java.io.*;
import java.nio.file.*;
import java.util.*;

// =============================================================
// 1. BASE CLASS (Rename + change fields based on domain)
// =============================================================
class Entity {
    private String id;
    private String name;
    private int value;

    // default constructor
    public Entity() {
        this("NA", "Unknown", 0);
    }

    // parameterized constructor
    public Entity(String name, int value) {
        this(UUID.randomUUID().toString(), name, value);
    }

    // overloaded constructor
    public Entity(String id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    // method overloading
    public void updateValue() { this.value++; }
    public void updateValue(int v) { this.value += v; }

    // getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getValue() { return value; }

    @Override
    public String toString() {
        return "Entity{id='" + id + "', name='" + name + "', value=" + value + "}";
    }
}


// =============================================================
// 2. ABSTRACT CLASS (Must implement abstract method later)
// =============================================================
abstract class AbstractProcessor {

    public void startProcessing() {
        System.out.println("Processing started...");
    }

    public abstract void process();
}


// =============================================================
// 3. INTERFACE (Rename as needed)
// =============================================================
interface Actionable {
    void performAction();
}


// =============================================================
// 4. CHILD CLASS USING INHERITANCE + OVERRIDING
// =============================================================
class SpecialEntity extends Entity {
    private String category;

    public SpecialEntity(String name, int value, String category) {
        super(name, value);
        this.category = category;
    }

    @Override
    public String getName() {
        return super.getName() + " [" + category + "]";
    }
}


// =============================================================
// 5. CHILD CLASS IMPLEMENTING INTERFACE
// =============================================================
class InteractiveEntity extends Entity implements Actionable {
    public InteractiveEntity(String name, int value) {
        super(name, value);
    }

    @Override
    public void performAction() {
        System.out.println(getName() + " performed an action.");
    }
}


// =============================================================
// 6. CHILD CLASS EXTENDING ABSTRACT CLASS
// =============================================================
class EntityProcessor extends AbstractProcessor {

    @Override
    public void process() {
        System.out.println("Abstract processing completed.");
    }
}


// =============================================================
// 7. THREADING CLASS
// =============================================================
class BackgroundTask implements Runnable {
    private String taskName;

    public BackgroundTask(String taskName){
        this.taskName = taskName;
    }

    @Override
    public void run(){
        try{
            for(int i = 1; i <= 5; i++){
                System.out.println("[" + taskName + "] step " + i);
                Thread.sleep(300);
            }
        } catch(Exception e){
            System.out.println("Thread interrupted");
        }
    }
}


// =============================================================
// 8. FILE HANDLING UTILITY
// =============================================================
class FileHelper {

    public static void writeText(String file, String data) throws Exception {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(data);
        }
    }

    public static void appendText(String file, String data) throws Exception {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(data);
        }
    }

    public static String readText(String file) throws Exception {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}


// =============================================================
// 9. MAIN CLASS — FULL DEMO INCLUDING LAMBDA
// =============================================================
// temp/Main/TemplateMain.java

// package temp.main;

// IMPORTS AS NEEDED

public class template {
    public static void main(String[] args) throws Exception {

        // A) Base Class + Constructor + Overloading
        Entity e = new Entity("Sample", 10);
        e.updateValue();
        e.updateValue(5);
        System.out.println("Entity: " + e);

        // B) Inheritance + Overriding
        SpecialEntity se = new SpecialEntity("Special", 20, "VIP");
        System.out.println("Special: " + se.getName());

        // C) Interface + Polymorphism
        Actionable ac = new InteractiveEntity("ActiveOne", 30);
        ac.performAction();

        // D) ABSTRACT CLASS
        AbstractProcessor processor = new EntityProcessor();
        processor.startProcessing();
        processor.process();

        // E) COLLECTIONS + GENERICS
        SortedSet<String> set = new TreeSet<>(Arrays.asList("Zed","Mike","Adam"));
        System.out.println("SortedSet: " + set);

        Queue<Entity> q = new ArrayDeque<>();
        q.add(new Entity("Q1", 5));
        q.add(new Entity("Q2", 10));
        System.out.println("Queue Poll: " + q.poll());

        List<Entity> list = new ArrayList<>();
        list.add(new Entity("A", 1));
        list.add(new Entity("B", 2));
        list.add(new Entity("C", 3));
        System.out.println("List: " + list);

        // F) LAMBDA EXPRESSIONS (Very Important)
        System.out.println("LAMBDA OUTPUT:");
        list.forEach(item -> System.out.println("Lambda Item: " + item.getName()));

        StringBuffer sb = new StringBuffer("Summary: ");
        list.forEach(item -> sb.append(item.getName()).append("(").append(item.getValue()).append(") "));
        System.out.println(sb);

        // G) THREADING
        Thread t = new Thread(new BackgroundTask("ProcessTask"));
        t.start();
        t.join();

        // H) NIO FILE WALK (Lambda used here too)
        Files.walk(Paths.get("."))
                .filter(Files::isRegularFile)
                .limit(5)
                .forEach(path -> System.out.println("FILE: " + path));

        // I) TEXT FILE OPERATIONS
        FileHelper.writeText("data.txt", "Hello File\n");
        FileHelper.appendText("data.txt", "More text\n");
        System.out.println("File Content:\n" + FileHelper.readText("data.txt"));
    }
}