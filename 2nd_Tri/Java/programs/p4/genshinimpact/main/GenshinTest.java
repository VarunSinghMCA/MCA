package genshinimpact.main;

import genshinimpact.interfaces.CharacterActions;
import genshinimpact.implementations.*;

public class GenshinTest {
    public static void main(String[] args) {
        System.out.println("===== Genshin Impact Characters =====");

        // Interface reference variables
        CharacterActions pyro = new PyroCharacter("Diluc", 80, 2500);
        CharacterActions hydro = new HydroCharacter("Barbara", 70, 1800);
        CharacterActions electro = new ElectroCharacter("Lisa", 75, 2000);

        System.out.println("\n--- Trial Character ---");

        // Display all character stats and actions
        testCharacter(pyro);
        testCharacter(hydro);
        testCharacter(electro);

    }

    // Utility method to test characters using interface reference
    private static void testCharacter(CharacterActions character) {
        character.displayStats();
        System.out.println("Actions:");
        character.attack();
        character.useElementalSkill();
        character.useElementalBurst();
        System.out.println("--------------------------------------\n");
    }
}
