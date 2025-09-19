// Class 1: Game
class Game {

    String gameName;
    String genre;
    String platform;
    int maxPlayers;
    double rating;

    // Default constructor
    Game() {
        gameName = "Unknown Game";
        genre = "Unknown";
        platform = "Unknown";
        maxPlayers = 0;
        rating = 0.0;
    }

    // Parameterized constructor 1
    Game(String name, String genre, String platform) {
        this.gameName = name;
        this.genre = genre;
        this.platform = platform;
        this.maxPlayers = 4;
        this.rating = 4.6;
    }

    // Parameterized constructor 2
    Game(String name, String genre, String platform, int maxPlayers, double rating) {
        this.gameName = name;
        this.genre = genre;
        this.platform = platform;
        this.maxPlayers = maxPlayers;
        this.rating = rating;
    }

    // Method 1: Display game info
    void displayGameInfo() {
        System.out.println("Game: " + gameName + "\nGenre: " + genre + "\nPlatform: " + platform + "\nMax Players: " + maxPlayers + "\nRating: " + rating);
    }

    // Method 2: Update rating
    void updateRating(double newRating) {
        rating = newRating;
        System.out.println("Rating updated for " + gameName + ": " + rating);
    }
}

// Class 2: Player
class Player {

    String playerName;
    int playerLevel;
    int score;
    String favoriteGame;
    boolean premiumMember;

    Player() {
        playerName = "Guest";
        playerLevel = 1;
        score = 0;
        favoriteGame = "None";
        premiumMember = false;
    }

    // Parameterized constructor 1
    Player(String name, int level, String favGame) {
        this.playerName = name;
        this.playerLevel = level;
        this.favoriteGame = favGame;
        this.score = 0;
        this.premiumMember = false;
    }

    // Parameterized constructor 2
    Player(String name, int level, int score, String favGame, boolean premium) {
        this.playerName = name;
        this.playerLevel = level;
        this.score = score;
        this.favoriteGame = favGame;
        this.premiumMember = premium;
    }

    // Method 1: Display player info
    void displayPlayerInfo() {
        System.out.println("Player: " + playerName + "\nLevel: " + playerLevel + "\nScore: " + score + "\nFavorite Game: " + favoriteGame + "\nPremium Member: " + premiumMember);
    }

    // Method 2: Increase player level
    void levelUp() {
        playerLevel++;
        System.out.println(playerName + " leveled up! New Level: " + playerLevel);
    }
}

// Class 3: Main class
public class PlayGame {
    public static void main(String[] args) {

        // Creating Game objects
        Game g1 = new Game();
        Game g2 = new Game("Legends Arena", "Action", "PC"); 
        Game g3 = new Game("Fantasy Quest", "RPG", "Mobile", 8, 4.5);

        // Creating Player objects
        Player p1 = new Player(); // default constructor
        Player p2 = new Player("Varun", 55, "Genshin Impact");
        Player p3 = new Player("Aditi", 10, 1200, "Fantasy Quest", true); 

        // Display info
        g1.displayGameInfo();
        g2.displayGameInfo();
        g3.displayGameInfo();

        p1.displayPlayerInfo();
        p2.displayPlayerInfo();
        p3.displayPlayerInfo();

        // Use methods
        g2.updateRating(4.8);
        p2.levelUp();
    }
}
