import java.util.*;

// ---------------- INTERFACES -----------------

interface Wishable {
    String rollItem();
}

interface WishStatistics {
    void record(String item);
    void printSummary();
}

// -------------- IMPLEMENTATION ---------------

class StandardBanner implements Wishable {

    private final List<String> fiveStarPool;
    private final List<String> fourStarPool;
    private final List<String> threeStarPool;

    private int pity4 = 0;
    private int pity5 = 0;

    public StandardBanner(List<String> fiveStarPool, List<String> fourStarPool, List<String> threeStarPool) {
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

        // ------------------ 5★ PITY CHECK ------------------
        if (pity5 >= 90) {
            pity5 = 0;
            pity4 = 0;
            return getRandom(fiveStarPool);
        }

        // ------------------ 4★ PITY CHECK ------------------
        if (pity4 >= 10) {
            pity4 = 0;
            pity5 = 0;
            double chance = random.nextDouble() * 100;
            if (chance < 0.6) { // 0.6% chance for 5★ during 4★ pity
                return getRandom(fiveStarPool);
            }
            return getRandom(fourStarPool);
        }

        // ------------------ NORMAL ROLL ------------------
        if (roll < 0.6) { // 0.6% base rate
            pity5 = 0;
            pity4 = 0;
            return getRandom(fiveStarPool);
        } else if (roll < 0.6 + 5.1) { // 5.1% base 4★
            pity4 = 0;
            pity5++;
            return getRandom(fourStarPool);
        } else {
            pity5++;
            pity4++;
            return getRandom(threeStarPool);
        }
    }

    private String getRandom(List<String> pool) {
        Random random = new Random();
        return pool.get(random.nextInt(pool.size()));
    }
}

// ---------------- Calculations -----------------

class WishTracker implements WishStatistics {

    private final Map<String, Integer> frequency = new HashMap<>();
    private final Set<String> uniqueDrops = new HashSet<>();
    private int totalPulls = 0;

    @Override
    public void record(String item) {
        totalPulls++;
        uniqueDrops.add(item);
        frequency.put(item, frequency.getOrDefault(item, 0) + 1);
    }

    @Override
    public void printSummary() {
        System.out.println("\n==== WISH SUMMARY ====");
        System.out.println("Total Pulls: " + totalPulls);

        System.out.println("\nUnique Items Obtained (" + uniqueDrops.size() + "):");
        for (String item : uniqueDrops) {
            System.out.println("- " + item);
        }

        System.out.println("\nItem Frequency:");
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " times");
        }
    }
}

// ---------------- MAIN PROGRAM -----------------

public class GenshinWishSimulation {

    public static void main(String[] args) {

        // ------------- ITEM POOLS -------------

        List<String> fiveStars = Arrays.asList(
            "Dehya", "Diluc", "Jean", "Keqing", "Mona", "Qiqi",
            "Tighnari", "Yumemizuki Mizuki",
            "Amos' Bow", "Aquila Favonia", "Lost Prayer", "Jade Spear",
            "Skyward Atlas", "Skyward Blade", "Skyward Harp",
            "Skyward Pride", "Skyward Spine", "Wolf's Gravestone"
        );

        List<String> fourStars = Arrays.asList(
            "Amber","Barbara","Beidou","Bennett","Candace","Charlotte","Chevreuse",
            "Chongyun","Collei","Dahlia","Diona","Dori","Faruzan","Fischl","Freminet",
            "Gaming","Gorou","Iansan","Ifa","Kachina","Kaeya","Kaveh","Kirara",
            "Kujou Sara","Kuki Shinobu","Lan Yan","Layla","Lisa","Lynette","Mika",
            "Ningguang","Noelle","Ororon","Razor","Rosaria","Sayu","Sethos","Heizou",
            "Sucrose","Thoma","Xiangling","Xingqiu","Xinyan","Yanfei","Yaoyao","Yun Jin",
            "Dragons' Bane","Eye of Perception","Favonius Codex","Favonius Greatsword",
            "Favonius Lance","Favonius Sword","Favonius Bow","Lion's Roar","Rainslasher",
            "Rust","Sac Bow","Sac Fragments","Sac Greatsword","Sac Sword","The Bell",
            "The Flute","The Stringless","The Widsith"
        );

        List<String> threeStars = Arrays.asList(
            "Black Tassel", "Bloodtainted Greatsword", "Cool Steel", "Debate Club",
            "Emerald Orb", "Ferrous Shadow", "Harbinger of Dawn", "Magic Guide",
            "Raven Bow", "Sharpshooter's Oath", "Skyrider Sword",
            "Slingshot", "Thrilling Tales"
        );

        StandardBanner banner = new StandardBanner(fiveStars, fourStars, threeStars);
        WishTracker tracker = new WishTracker();

        // ----------- PREDEFINED SIMULATIONS -----------
        simulateWishes(banner, tracker, 1);
        simulateWishes(banner, tracker, 10);
        simulateWishes(banner, tracker, 20);
        simulateWishes(banner, tracker, 120);

        tracker.printSummary();
    }

    public static List<String> simulateWishes(StandardBanner banner, WishTracker tracker, int n) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String item = banner.rollItem();
            tracker.record(item);
            result.add(item);
        }

        return result;
    }

}
