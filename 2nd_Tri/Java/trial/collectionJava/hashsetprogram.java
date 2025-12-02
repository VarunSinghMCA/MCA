import java.util.*;

public class hashsetprogram {
    public static void main(String[] args) {


        Set<Integer> ownedCharacters = new HashSet<>();
        ownedCharacters.add(101);  // Traveler
        ownedCharacters.add(102);  // Amber
        ownedCharacters.add(102);  // duplicate wish - ignored
        ownedCharacters.add(103);  // Kaeya

        Set<Integer> newWishes = new HashSet<>();
        newWishes.add(104);  // Lisa
        newWishes.add(105);  // Diluc

        ownedCharacters.addAll(newWishes);

        System.out.println("Characters in your roster (HashSet - Unordered): " + ownedCharacters);


        // Remove character from roster
        ownedCharacters.remove(101);
        System.out.println("After removing Traveler (101): " + ownedCharacters);


        // Check if Character Exists
        boolean hasCharacter = ownedCharacters.contains(103);
        System.out.println("Do you own Kaeya (103)? " + hasCharacter);


        // Convert HashSet to ArrayList
        List<Integer> characterList = new ArrayList<>(ownedCharacters);
        System.out.println("Character roster as ArrayList: " + characterList);
        

        // Artifacts
        Set<String> artifactSets = new HashSet<>();
        artifactSets.add("Gladiator's Finale");
        artifactSets.add("Viridescent Venerer");
        artifactSets.add("Crimson Witch of Flames");
        artifactSets.add("Noblesse Oblige");

        System.out.println("\nArtifact Sets in Inventory (HashSet - Unordered): " + artifactSets);

        artifactSets.remove("Crimson Witch of Flames");
        System.out.println("After using Crimson Witch for upgrade: " + artifactSets);

        Set<String> setsToRemove = new HashSet<>();
        setsToRemove.add("Gladiator's Finale");
        setsToRemove.add("Viridescent Venerer");

        artifactSets.removeAll(setsToRemove);
        System.out.println("After removing multiple artifact sets: " + artifactSets);
        
    }
}