class SharedWallet {
    private int primogems = 0;

    // Traveler adds primogems
    public synchronized void addPrimogems(int amount) {
        primogems += amount;
        System.out.println("Traveler collected " + amount + " primogems. Total: " + primogems);
        notify(); // Wake Paimon if she is waiting
    }

    // Paimon spends primogems
    public synchronized void spendPrimogems(int amount) {
        while (primogems < amount) {
            System.out.println("Paimon wants to make a wish but not enough primogems... waiting.");
            try { wait(); } catch (Exception e) {}
        }
        primogems -= amount;
        System.out.println("Paimon spent " + amount + " primogems for a wish! Remaining: " + primogems);
    }
}

// Thread class
class Traveler extends Thread {
    SharedWallet wallet;

    Traveler(SharedWallet wallet) {
        this.wallet = wallet;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            wallet.addPrimogems(20);
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    }
}

// Runnable interface
class Paimon implements Runnable {
    SharedWallet wallet;

    Paimon(SharedWallet wallet) {
        this.wallet = wallet;
    }

    public void run() {
        wallet.spendPrimogems(50);
    }
}

public class genshinThread {
    public static void main(String[] args) {
        SharedWallet wallet = new SharedWallet();

        Traveler traveler = new Traveler(wallet);
        Thread paimon = new Thread(new Paimon(wallet));

        System.out.println("=== Genshin Impact Multithreading Demo ===\nStarting threads...\n");

        traveler.start();
        paimon.start();
    }
}
