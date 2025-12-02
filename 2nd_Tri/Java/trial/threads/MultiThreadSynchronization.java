/**
 * Genshin Impact Mora Treasury - Shared resource that needs synchronization
 * Multiple players can farm Mora and spend it on upgrades simultaneously
 */
class MoraTreasury {
    private int moraBalance = 5000; // Initial Mora amount
    private int crystalOre = 20;    // Shared crystal ore for weapon enhancement

    // Synchronized method to earn Mora from commissions/domains
    public synchronized void earnMora(int amount, String activity) {
        System.out.println(Thread.currentThread().getName() + " completed " + activity + 
                          " and earned " + amount + " Mora");
        moraBalance += amount;
        System.out.println("Current Mora Treasury: " + moraBalance);
        
        // Notify waiting threads that resources are available
        notifyAll();
    }

    // Synchronized method to spend Mora on upgrades
    public synchronized void spendMora(int amount, String item) {
        while (moraBalance < amount) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting for sufficient Mora to upgrade " + item + " (Need: " + amount + ", Have: " + moraBalance + ")");
                wait(); // Wait until more Mora is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        System.out.println(Thread.currentThread().getName() + 
                          " is upgrading " + item + " for " + amount + " Mora");
        moraBalance -= amount;
        System.out.println("Mora after upgrade: " + moraBalance);
    }

    // Synchronized method to mine crystal ore
    public synchronized boolean mineCrystalOre(int amount) {
        if (crystalOre >= amount) {
            System.out.println(Thread.currentThread().getName() + " mined " + amount + " Crystal Ore");
            crystalOre -= amount;
            System.out.println("Remaining Crystal Ore: " + crystalOre);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to mine " + amount + " Crystal Ore but only " + crystalOre + " available!");
            return false;
        }
    }

    // Get current balances
    public synchronized int getMoraBalance() {
        return moraBalance;
    }
    
    public synchronized int getCrystalOre() {
        return crystalOre;
    }
}

/**
 * Genshin Impact Player Thread - Represents a player performing various activities
 */
class PlayerThread extends Thread {
    private MoraTreasury treasury;
    private String playerType;

    public PlayerThread(MoraTreasury treasury, String playerName, String playerType) {
        super(playerName);
        this.treasury = treasury;
        this.playerType = playerType;
    }

    @Override
    public void run() {
        try {
            switch (playerType) {
                case "FARMER":
                    performFarmingActivities();
                    break;
                case "FIGHTER":
                    performCombatActivities();
                    break;
                case "CRAFTER":
                    performCraftingActivities();
                    break;
                default:
                    performMixedActivities();
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted!");
            Thread.currentThread().interrupt();
        }
    }

    private void performFarmingActivities() throws InterruptedException {
        // Farm Mora from various sources
        treasury.earnMora(800, "Daily Commissions");
        Thread.sleep(1000); // Simulate time taken
        
        treasury.earnMora(1200, "Ley Line Outcrop");
        Thread.sleep(800);
        
        // Try to mine crystal ore
        treasury.mineCrystalOre(3);
        Thread.sleep(500);
        
        // Spend on character ascension
        treasury.spendMora(2000, "Character Ascension Materials");
    }

    private void performCombatActivities() throws InterruptedException {
        // Earn Mora from domains and bosses
        treasury.earnMora(1500, "Violet Court Domain");
        Thread.sleep(1200);
        
        treasury.earnMora(2000, "Weekly Boss (Childe)");
        Thread.sleep(1500);
        
        // Spend on weapon enhancement
        treasury.spendMora(2500, "5★ Weapon Enhancement");
        Thread.sleep(700);
        
        // Try to get more crystal ore for weapon crafting
        treasury.mineCrystalOre(5);
    }

    private void performCraftingActivities() throws InterruptedException {
        // Mine crystal ore first
        treasury.mineCrystalOre(4);
        Thread.sleep(600);
        
        // Earn some Mora from selling artifacts
        treasury.earnMora(600, "Artifact Sales");
        Thread.sleep(500);
        
        // Spend on artifact enhancement
        treasury.spendMora(1800, "Artifact Enhancement");
        Thread.sleep(900);
        
        // More mining
        treasury.mineCrystalOre(2);
    }

    private void performMixedActivities() throws InterruptedException {
        // Mixed gameplay
        treasury.earnMora(1000, "Exploration Chests");
        Thread.sleep(800);
        
        treasury.spendMora(1500, "Talent Level Up");
        Thread.sleep(600);
        
        treasury.mineCrystalOre(3);
        Thread.sleep(400);
        
        treasury.earnMora(700, "Event Rewards");
    }
}

/**
 * Genshin Impact Multi-Player Synchronization Demo
 * Demonstrates thread synchronization in a shared gaming environment
 */
public class MultiThreadSynchronization {
    public static void main(String[] args) {
        System.out.println("========= GENSHIN IMPACT: MULTI-PLAYER TREASURY SYSTEM =========");
        System.out.println("Multiple players sharing Mora Treasury and Crystal Ore resources");
        System.out.println("Demonstrating thread synchronization with wait(), notify(), and synchronized methods\n");
        System.out.println("================================================================\n");
        // Shared treasury resource that all players access
        MoraTreasury sharedTreasury = new MoraTreasury();

        // Create different types of players (threads)
        PlayerThread traveler = new PlayerThread(sharedTreasury, "Traveler", "FIGHTER");
        PlayerThread diluc = new PlayerThread(sharedTreasury, "Diluc", "FARMER");
        PlayerThread albedo = new PlayerThread(sharedTreasury, "Albedo", "CRAFTER");
        PlayerThread zhongli = new PlayerThread(sharedTreasury, "Zhongli", "MIXED");
        PlayerThread venti = new PlayerThread(sharedTreasury, "Venti", "FARMER");

        System.out.println("Starting multi-player activities...\n");

        // Start all player threads simultaneously
        traveler.start();
        diluc.start();
        albedo.start();
        zhongli.start();
        venti.start();

        // Wait for all threads to complete
        try {
            traveler.join();
            diluc.join();
            albedo.join();
            zhongli.join();
            venti.join();

            // Display final treasury status
            System.out.println("\n===== FINAL TREASURY STATUS =====");
            System.out.println("Final Mora Balance: " + sharedTreasury.getMoraBalance());
            System.out.println("Remaining Crystal Ore: " + sharedTreasury.getCrystalOre());
            System.out.println("\nAll players have completed their activities!");
            System.out.println("Thread synchronization prevented resource conflicts!");

        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}