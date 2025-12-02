// public class GenshinGeneric<T1, T2> {
//     private T1 weapon;
//     private T2 artifact;

//     public GenshinGeneric(T1 weapon, T2 artifact) {
//         this.weapon = weapon;
//         this.artifact = artifact;
//     }

//     public void displayItems() {
//         System.out.println("Weapon: " + weapon);
//         System.out.println("Artifact: " + artifact);
//     }

//     public void compareItems() {
//         if (weapon.equals(artifact))
//             System.out.println("Both are same (rare!)");
//         else
//             System.out.println("Different item types.");
//     }

//     public static void main(String[] args) {
//         GenshinGeneric<String, String> items = new GenshinGeneric<>("Aquila Favonia", "Gladiator's Finale");

//         System.out.println("=== Genshin Impact Inventory ===");
//         items.displayItems();
//         items.compareItems();
//     }
// }

interface GenericInterface<C>{
    void showChar(C character);
}

class GenshinGenericChar<C,E,L> implements GenericInterface<C> {
    private C character;
    private E element;
    private L level;

    public GenshinGenericChar(C character, E element, L level) {
        this.character = character;
        this.element = element;
        this.level = level;
    }

    public void getCharacterInfo() {
        System.out.println("--------Char Info---------");
        System.out.println("Character: " + character);
        System.out.println("Element: " + element);
        System.out.println("Level: " + level);
        System.out.println("--------------------------");
    }

    public void setCharInfo(C character, E element, L level){
        this.character = character;
        this.element = element;
        this.level = level;
        System.out.println("\n-----Char Info Update-----");
        System.out.println("Character info updated with \nCharacter: " + character + ",\nElement: " + element + ",\nLevel: " + level);
        System.out.println("--------------------------");
    }

    public <E> E displayElement(E element){
        System.out.println("\nElement displayed from generic method: " + element + "\n");
        return element;
    }

    public void showChar(C character){
        System.out.println("Character from interface method: " + character);
    }
}

public class GenshinGeneric{
    public static void main(String[] args){
        GenshinGenericChar<String, String, Integer> char1 = new GenshinGenericChar<String, String, Integer>("Bennett", "Pyro", 45);
        char1.getCharacterInfo();
        char1.setCharInfo("Diluc", "Pyro", 80);
        char1.displayElement("Electro");

        GenshinGenericChar<String, String, Integer> char2 = new GenshinGenericChar<>("Mona", "Hydro", 70);
        char2.getCharacterInfo();
        char2.displayElement("Hydro");
        char2.showChar("Qiqi");

        GenshinGenericChar<String, String, Integer> char3 = new GenshinGenericChar<>("Xiao", "Anemo", 90);
        char3.getCharacterInfo();
    }
}