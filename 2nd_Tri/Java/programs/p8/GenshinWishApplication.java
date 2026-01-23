import java.io.*;
import java.util.*;


// checked - exceptions
class FileLoadException extends Exception {
    public FileLoadException(String msg) {
        super(msg);
    }
}

class DataFormatException extends Exception {
    public DataFormatException(String msg) {
        super(msg);
    }
}

// my - exception - unchecked
class InvalidWishInputException extends RuntimeException {
    public InvalidWishInputException(String msg) {
        super(msg);
    }
}

interface Wishable {
    String rollItem();`
}

interface WishStatistics {
    void record(String item);
    void printSummary();
}

// Standard Banner
class StandardBanner implements Wishable {

    private final List<String> fiveStarPool;
    private final List<String> fourStarPool;
    private final List<String> threeStarPool;

    private int pity4 = 0;
    private int pity5 = 0;

    public StandardBanner(List<String> fiveStarPool, List<String> fourStarPool, List<String> threeStarPool) {

        // my - exception - unchecked - logical
        if (fiveStarPool == null || fourStarPool == null || threeStarPool == null)
            throw new InvalidWishInputException("Item pools cannot be null.");

        this.fiveStarPool = fiveStarPool;
        this.fourStarPool = fourStarPool;
        this.threeStarPool = threeStarPool;
    }

    @Override
    public String rollItem() {

        pity4++;
        pity5++;

        Random random = new Random();
        double roll = random.nextDouble() * 100;

        if (pity5 >= 90) {
            pity5 = 0;
            pity4 = 0;
            return getRandom(fiveStarPool);
        }

        if (pity4 >= 10) {
            pity4 = 0;
            pity5 = 0;
            if (random.nextDouble() * 100 < 0.6)
                return getRandom(fiveStarPool);

            return getRandom(fourStarPool);
        }

        if (roll < 0.6) {
            pity5 = 0;
            pity4 = 0;
            return getRandom(fiveStarPool);
        } else if (roll < 5.7) {
            pity4 = 0;
            return getRandom(fourStarPool);
        } else {
            return getRandom(threeStarPool);
        }
    }

    private String getRandom(List<String> pool) {
        if (pool.isEmpty())
            throw new InvalidWishInputException("Item pool is empty. Cannot roll item.");

        Random random = new Random();
        return pool.get(random.nextInt(pool.size()));
    }
}

class WishTracker implements WishStatistics {

    private final Map<String, Integer> frequency = new HashMap<>();
    private int totalPulls = 0;

    @Override
    public void record(String item) {
        if (item == null)
            throw new InvalidWishInputException("Cannot record null item.");

        totalPulls++;
        frequency.put(item, frequency.getOrDefault(item, 0) + 1);
    }

    @Override
    public void printSummary() {
        System.out.println("\n====== WISH SUMMARY ======");
        System.out.println("Total Pulls: " + totalPulls);

        System.out.println("\nItem Frequency:");
        frequency.forEach((item, count) ->
            System.out.println(item + " -> " + count + " times"));
    }
}

// Simple File Reader - reads items from text file
class ItemFileLoader {

    public static Map<String, List<String>> loadItemPools(String filename) throws FileLoadException, DataFormatException {
        File file = new File(filename);
        // Check if file exists
        if (!file.exists()) {
            throw new FileLoadException("File not found at path: " + filename);
        }

        System.out.println("File exists at path: " + filename);

        // Create 3 empty lists to store different star items
        List<String> fiveStarItems = new ArrayList<>();   // 5-star characters
        List<String> fourStarItems = new ArrayList<>();   // 4-star characters
        List<String> threeStarItems = new ArrayList<>();  // 3-star weapons

        // Track which section we're currently reading
        String currentCategory = null;  // Will be "FIVE_STAR", "FOUR_STAR", or "THREE_STAR"

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                line = line.trim();

                // empty lines skip
                if (line.length() == 0) {
                    continue;
                }

                // Check if this line is a category header
                if (line.equals("FIVE_STAR")) {
                    currentCategory = "FIVE_STAR";
                    continue;
                }
                
                if (line.equals("FOUR_STAR")) {
                    currentCategory = "FOUR_STAR";
                    continue;
                }
                
                if (line.equals("THREE_STAR")) {
                    currentCategory = "THREE_STAR";
                    continue;
                }

                if (currentCategory == null) {
                    throw new DataFormatException("File must start with FIVE_STAR, FOUR_STAR, or THREE_STAR");
                }

                // Add item to the correct list based on current category
                if (currentCategory.equals("FIVE_STAR")) {
                    fiveStarItems.add(line);
                } else if (currentCategory.equals("FOUR_STAR")) {
                    fourStarItems.add(line);
                } else if (currentCategory.equals("THREE_STAR")) {
                    threeStarItems.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            throw new FileLoadException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new FileLoadException("Error reading file: " + e.getMessage());
        }

        // Step 5: Put all lists into a Map (like a dictionary) and return
        Map<String, List<String>> allPools = new HashMap<>();
        allPools.put("FIVE_STAR", fiveStarItems);
        allPools.put("FOUR_STAR", fourStarItems);
        allPools.put("THREE_STAR", threeStarItems);

        return allPools;
    }
}

public class GenshinWishApplication {

    public static void main(String[] args) {

        try {

            Map<String, List<String>> pools =
                    ItemFileLoader.loadItemPools("./items.txt");

            StandardBanner banner = new StandardBanner(
                    pools.get("FIVE_STAR"),
                    pools.get("FOUR_STAR"),
                    pools.get("THREE_STAR")
            );

            WishTracker tracker = new WishTracker();

            simulate(banner, tracker, 10);
            simulate(banner, tracker, 90);
            simulate(banner, tracker, 150);

            tracker.printSummary();

        } catch (FileLoadException | DataFormatException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void simulate(StandardBanner banner, WishTracker tracker, int n) {
        if (n <= 0)
            throw new InvalidWishInputException("Number of pulls must be positive.");

        for (int i = 0; i < n; i++)
            tracker.record(banner.rollItem());
    }
}
