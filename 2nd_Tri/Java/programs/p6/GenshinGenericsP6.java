interface GenericInterface<C> {
    void showChar(C character);
}

// To remove the functionalInterface annotation error
@FunctionalInterface
interface WishCalculator {
    int calculate(int primogems, int wishes);
}

// If you we want to remove the FunctionalInterfave we have to change the lamda to an anonymous class or use IntBinaryOperator from java.util.function package

// Example:
// WishCalculator simpleWish = new WishCalculator() {
//     @Override
//     public int calculate(int primogems, int wishes) {
//         int cost = wishes * 160;
//         if (primogems < cost) {
//             System.out.println("Not enough primogems!");
//             return primogems;
//         }
//         int remaining = primogems - cost;
//         System.out.println("\n========= Wish Result =========");
//         System.out.println("Wishes made: " + wishes);
//         System.out.println("Primogems spent: " + cost);
//         System.out.println("Primogems left: " + remaining);
//         System.out.println("================================\n");
//         return remaining;
//     }
// };


class GenshinGenericChar<C, E, L> implements GenericInterface<C> {
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

    public void setCharInfo(C character, E element, L level) {
        this.character = character;
        this.element = element;
        this.level = level;
        System.out.println("\n-----Char Info Update-----");
        System.out.println("Character: " + character + "\nElement: " + element + "\nLevel: " + level);
        System.out.println("--------------------------");
    }

    public <T> T displayElement(T element) {
        System.out.println("\nElement displayed from generic method: " + element + "\n");
        return element;
    }

    @Override
    public void showChar(C character) {
        System.out.println("Character from interface method: " + character);
    }
}

// +----------------------------------------------+
// |              WISH SYSTEM CLASS               |
// +----------------------------------------------+
class WishSystem {

    // Simple Lambda Expression
    WishCalculator simpleWish = (primos, wishCount) -> {
        int cost = wishCount * 160;

        if (primos < cost) {
            System.out.println("Not enough primogems!");
            return primos;
        }

        int remaining = primos - cost;

        System.out.println("\n========= Wish Result =========");
        System.out.println("Wishes made: " + wishCount);
        System.out.println("Primogems spent: " + cost);
        System.out.println("Primogems left: " + remaining);
        System.out.println("================================\n");

        return remaining;
    };

    // Block Lambda Expression
    WishCalculator detailedWish = (primos, wishCount) -> {
        System.out.println("\nAttempting " + wishCount + " wishes...");

        int cost = wishCount * 160;

        if (primos < cost) {
            System.out.println("Not enough primogems!");
            return primos;
        }

        int remaining = primos - cost;

        System.out.println("Wish successful!");
        System.out.println("Cost: " + cost);
        System.out.println("Remaining Primogems: " + remaining);

        return remaining;
    };
}

// +----------------------------------------------+
// |                MAIN CLASS                    |
// +----------------------------------------------+
public class GenshinGenericsP6 {
    public static void main(String[] args) {

        GenshinGenericChar<String, String, Integer> char1 = new GenshinGenericChar<>("Bennett", "Pyro", 45);
        char1.getCharacterInfo();

        char1.setCharInfo("Diluc", "Pyro", 80);
        char1.displayElement("Electro");

        GenshinGenericChar<String, String, Integer> char2 = new GenshinGenericChar<>("Mona", "Hydro", 70);
        char2.getCharacterInfo();
        char2.displayElement("Hydro");
        char2.showChar("Qiqi");

        GenshinGenericChar<String, String, Integer> char3 = new GenshinGenericChar<>("Xiao", "Anemo", 90);
        char3.getCharacterInfo();

        // +----------------------------------------------+
        // |        WISH CALCULATION USING LAMBDAS        |
        // +----------------------------------------------+
        WishSystem wishSys = new WishSystem();

        int primogems = 10000;

        System.out.println("\nStarting Primogems: " + primogems);

        // 10
        primogems = wishSys.simpleWish.calculate(primogems, 10);
        System.out.println("Primogems after 10 pull: " + primogems);

        // 20
        primogems = wishSys.detailedWish.calculate(primogems, 20);

        System.out.println("\n=============================");
        System.out.println("Primogems Left: " + primogems);
        System.out.println("=============================");
    }
}
