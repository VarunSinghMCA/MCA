// GenshinImpact.java
// Demonstration of public, private, and default access modifiers
// Domain: Gaming Platform - Genshin Impact

class GenshinAccount {
    // Private variable
    private String accountPassword = "PaimonIsBest123"; // cannot access outside the class

    // Default (package-private) variable
    String gmailId = "traveler.aether@genshin.com"; // accessible within same package/file

    // Public variable
    public String region = "Asia Server"; // accessible from anywhere

    //Private method
    private void showPassword() {
        System.out.println("Account Password: " + accountPassword);
    }

    // Default (package-private) method
    void showGmailId() {
        System.out.println("Gmail ID: " + gmailId);
    }

    // Public method
    public void showRegion() {
        System.out.println("Region: " + region);
    }

    // Public method to indirectly display password
    public void displayPassword() {
        showPassword();
    }
}

public class GenshinImpact {
    public static void main(String[] args) {
        // Create an object of GenshinAccount
        GenshinAccount acc = new GenshinAccount();

        // ============ Accessing variables ============
        // Private variable – NOT ACCESSIBLE (will cause error if uncommented)
        // System.out.println(acc.accountPassword);

        // Default variable – Accessible (same file & package)
        System.out.println("Gmail ID (default): " + acc.gmailId);

        // Public variable – Accessible everywhere
        System.out.println("Region (public): " + acc.region);

        // ============ Accessing methods ============
        // Private method – NOT ACCESSIBLE (will cause error if uncommented)
        // acc.showPassword();

        // Default method – Accessible (same file & package)
        acc.showGmailId();

        // Public method – Accessible everywhere
        acc.showRegion();

        // Indirectly displaying password via public method
        acc.displayPassword();
    }
}
