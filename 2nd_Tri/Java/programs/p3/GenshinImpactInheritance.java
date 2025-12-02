class Character {
    // final variable - cannot be changed
    final String GAME_NAME = "Genshin Impact";

    protected String name;
    protected int level;
    protected String element;

    // Constructor
    public Character(String name, int level, String element) {
        this.name = name;
        this.level = level;
        this.element = element;
    }

    public void displayDetails() {
        System.out.println("=========================");
        System.out.println("    Character Details    ");
        System.out.println("=========================");
        System.out.println("Game: " + GAME_NAME);
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("Element: " + element);
    }

    // final method - cannot be overridden by subclasses
    public final void showGamePolicy() {
        System.out.println("Game Policy: All characters belong to the world of Teyvat and obey the Archons.");
    }
}

// Subclass 1 [Warrior]
class Warrior extends Character {
    private String weaponType;
    private int attackPower;

    public Warrior(String name, int level, String element, String weaponType, int attackPower) {
        // Using super to call superclass constructor
        super(name, level, element);
        this.weaponType = weaponType;
        this.attackPower = attackPower;
    }

    // Override displayDetails (but cannot override final method)
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Role: Warrior");
        System.out.println("Weapon Type: " + weaponType);
        System.out.println("Attack Power: " + attackPower);
    }
}

// Subclass 2 [Mage]
class Mage extends Character {
    private String spellType;
    private int manaPower;

    public Mage(String name, int level, String element, String spellType, int manaPower) {
        // Using super keyword to call parent constructor
        super(name, level, element);
        this.spellType = spellType;
        this.manaPower = manaPower;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Role: Mage");
        System.out.println("Spell Type: " + spellType);
        System.out.println("Mana Power: " + manaPower);
    }
}

// Final class (cannot be inherited)
final class Archon {
    public void showArchonMessage() {
        System.out.println("Archons are the supreme beings who rule over each region of Teyvat.");
    }
}

// Main class
public class GenshinImpactInheritance {
    public static void main(String[] args) {
        // Creating Warrior object
        Warrior diluc = new Warrior("Diluc", 90, "Pyro", "Claymore", 850);
        diluc.displayDetails();
        diluc.showGamePolicy();

        System.out.println();

        // Creating Mage object
        Mage lisa = new Mage("Lisa", 80, "Electro", "Lightning Rose", 700);
        lisa.displayDetails();
        lisa.showGamePolicy();

        System.out.println();

        // Using final class Archon
        Archon venti = new Archon();
        venti.showArchonMessage();
    }
}
