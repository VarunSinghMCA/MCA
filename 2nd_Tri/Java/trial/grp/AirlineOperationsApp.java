import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/* ------------------------------
   Abstract Base Class: FlightOperation
   ------------------------------ */
abstract class FlightOperation {
    protected String operationName;
    protected int maxScore;

    public FlightOperation() {
        this("__________", 100);   // (1) maxScore 
    }

    public FlightOperation(String operationName, int maxScore) {
        this.operationName = __________;   // (2) operationName
        this.maxScore = maxScore;
    }

    public void startOperation() {
        System.out.println("Starting " + __________ + "...");  // (3) operationName
    }

    public void startOperation(String flightNo) {
        System.out.println("Initiating " + operationName + " for Flight " + __________);  // (4) flightNo
    }

    public abstract __________ performOperation();  // (5) void/int
}

/* ------------------------------
   Multilevel Inheritance
   ------------------------------ */
class FuelCheck extends FlightOperation {
    public FuelCheck() {
        super("Fuel Check", __________);   // (6) operation name
    }

    @Override
    public int performOperation() {
        System.out.println("[FuelCheck] Checking fuel levels...");
        int fuelLiters = __________;       // (7) any int value, Ex. 20000
        int score = (fuelLiters >= 20000) ? __________ : 70;  // (8) 100 / 30
        System.out.println("[FuelCheck] Fuel score: " + score);
        return score;
    }
}

class AdvancedFuelCheck extends FuelCheck {
    @Override
    public int performOperation() {
        int base = super.__________();   // (9) FuelCheck's performOperation
        System.out.println("[AdvancedFuelCheck] Checking altitude burn rates...");
        int adjusted = Math.min(100, base + __________);   // (10) int value, Ex. 5
        return adjusted;
    }
}

/* ------------------------------
   Final Class
   ------------------------------ */
final class SafetyInspection extends FlightOperation {
    public SafetyInspection() {
        super("Safety Inspection", 100);
    }

    @Override
    public int performOperation() {
        System.out.println("[SafetyInspection] Running emergency equipment check...");
        return __________;  // (11) safety score, Ex. 90
    }
}

/* ------------------------------
   Interfaces
   ------------------------------ */
interface CrewOperation {
    int __________();   // (12) runCheck
}

interface AdvancedCrewOperation extends CrewOperation {
    void __________();  // (13) showCrewInstructions
}

/* ------------------------------
   Implementation Class
   ------------------------------ */
class CrewPerformanceTest implements AdvancedCrewOperation {
    @Override
    public void showCrewInstructions() {
        System.out.println("[CrewPerformance] Follow standard airline crew readiness checklist.");
    }

    @Override
    public int runCheck() {
        int readiness = __________;   // (14) int value, Ex. 95
        return readiness;
    }
}

/* ------------------------------
   Generic Functional Interface
   ------------------------------ */
@FunctionalInterface
interface ScoreCalculator<T> {
    T __________();   // (15) normalize
}

/* ------------------------------
   Generic Class
   ------------------------------ */
class GenericScoreEngine<T extends Number> {
    public double normalize(T score, T max) {
        return (score.doubleValue() / max.doubleValue()) * __________;  // (16) 100
    }
}

/* ------------------------------
   Event Buffer for Multithreading
   ------------------------------ */
class EventBuffer {
    private LinkedList<String> list = new LinkedList<>();
    private final int capacity;
    private boolean finished = false;

    public EventBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(String event) throws InterruptedException {
        while (list.size() == capacity) __________;   // (17) wait();
        list.add(event);
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (list.isEmpty()) {
            if (finished) return null;
            __________;   // (18) wait();
        }
        String ev = list.removeFirst();
        notifyAll();
        return ev;
    }

    public synchronized void setFinished() {
        finished = true;
        notifyAll();
    }
}

/* ------------------------------
   Air Traffic Monitor Thread
   ------------------------------ */
class AirTrafficMonitor extends Thread {
    private final EventBuffer buffer;

    public AirTrafficMonitor(EventBuffer buffer) {
        this.buffer = buffer;
        setName("AirTrafficMonitor");
    }

    @Override
    public void run() {
        try {
            String[] statuses = {"Landing", "Take-Off", "Holding Pattern", "Taxiing"};
            for (int i = 0; i < __________; i++) {   // (19) statuses.length * 2
                String event = "[ATC] FlightStatus: " + statuses[new Random().nextInt(statuses.length)];
                System.out.println(event);
                buffer.put(event);
                Thread.sleep(__________);           // (20) 1000 (ms)
            }
        } catch (Exception e) {}
    }
}


public class AirlineOperationsApp {
    public static void main(String[] args) {

        FlightOperation operation = new AdvancedFuelCheck();
        operation.startOperation("__________");   // (21) any flight number, Ex. "AI202"
        int fuelScore = operation.__________();   // (22) performOperation

        SafetyInspection safety = new SafetyInspection();
        int safetyScore = safety.__________();    // (23) performOperation

        CrewPerformanceTest crew = new CrewPerformanceTest();
        int crewScore = crew.__________();        // (24) runCheck

        final int bonus = __________;             // (25) 5
        ScoreCalculator<Integer> calc =
                () -> fuelScore + safetyScore + crewScore + bonus;

        int total = calc.__________();            // (26) normalize

        GenericScoreEngine<Integer> engine = new GenericScoreEngine<>();
        double normalized = engine.normalize(total, __________);  // (27) 300

        System.out.println("Final Score: " + normalized);
    }
}
