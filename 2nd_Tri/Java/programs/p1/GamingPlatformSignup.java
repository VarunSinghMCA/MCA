import java.io.*;

// +------------------------------------------+
// |                User Class                |  
// +------------------------------------------+
class User {
    String username;
    String password;

    // Parameterized Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// +------------------------------------------+
// |            Validator Class               |
// +------------------------------------------+
class Validator {

    private String[] existingUsernames; 

    // Constructor: initialize with existing usernames
    public Validator(String[] existingUsernames) {
        this.existingUsernames = existingUsernames;
    }

    // Method to check username availability
    public boolean isUsernameTaken(String username) {
        for (String user : existingUsernames) {
            //===================================================
            // equalsIgnoreCase() to compare strings ignoring case
            //===================================================
            if (user.equalsIgnoreCase(username)) { 
                return true;
            }
        }
        return false;
    }

    // Method: Validate password
    public String validatePassword(String password) {
        //===============================================================
        // String holding special characters not allowed at start       |
        //===============================================================
        String specialChars = "@!#$%";

        //===============================================================
        // Check length of password using length()                      |
        //===============================================================
        if (password.length() < 4 || password.length() > 12) {
            return "Password must be between 4 and 12 characters.";
        }

        //===================================================
        // charAt(0) to get first character of password     |
        //===================================================
        char firstChar = password.charAt(0);

        //===================================================
        // indexOf() to check if first character is a special character
        //===================================================
        if (specialChars.indexOf(firstChar) != -1) {
            return "Password should not start with special characters like @, !, #, $, %.";
        }

        // 200: OK
        return "VALID";
    }
}

// +------------------------------------------+
// |                Main Class                |   
// +------------------------------------------+
public class GamingPlatformSignup {
    public static void main(String[] args) throws IOException {
        //===================================================
        // Array of existing usernames                      |
        //===================================================
        String[] existingUsernames = {"playerOne", "gamerX", "ninja123", "dragonSlayer"};

        //===================================================
        // New Validator object                             |
        //===================================================
        Validator validator = new Validator(existingUsernames);
        
        //===================================================================================
        // BufferedReader for efficient reading of characters, arrays, and lines.           |
        //===================================================================================
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //===================================================
        // LOOP until user exits the program manually       |
        //===================================================
        while (true) {
            System.out.println("\n+------------------------------------------+");
            System.out.println("\n|          Gaming Platform Signup          |");
            System.out.println("\n+------------------------------------------+");
            System.out.println("Type 'exit' as username to stop the program.");

            //===================================================
            // User input for username                          | 
            //===================================================
            System.out.print("Enter Username: ");
            String username = br.readLine();

            //===================================================
            // equalIgnoreCase() for Exit condition             |
            //===================================================
            if (username.equalsIgnoreCase("exit")) {
                System.out.println("Program stopped by user.");
                break;
            }

            //===================================================
            // User input for password                          |
            //===================================================
            System.out.print("Enter Password: ");
            String password = br.readLine();

            //===================================================
            // Validate username using Validator class          |
            //===================================================
            if (validator.isUsernameTaken(username)) {
                System.out.println("Bruh: Username already taken. Please try a different one.");
                continue; 
            }

            //===================================================
            // Validate password using Validator class          |
            //===================================================
            String passwordCheck = validator.validatePassword(password);

            //===================================================================
            // equals() to compare strings (check if password valid)            | 
            //===================================================================
            if (!passwordCheck.equals("VALID")) {
                System.out.println("Bruh: " + passwordCheck);
                continue;
            }

            //===================================================
            // If both valid, create User object                |
            //===================================================
            User newUser = new User(username, password);

            //===================================================
            // Using StringBuffer to show key:value pair data   |
            //===================================================
            StringBuffer userData = new StringBuffer();
            //===================================================
            // append() to add strings to StringBuffer          |
            //===================================================
            userData.append("username=").append(newUser.username);
            userData.append(", ");
            userData.append("password=").append(newUser.password);

            System.out.println("OogaBooga: Registration Successful!");
            //===================================================================
            // toString() to convert StringBuffer to String for output          |
            //===================================================================
            System.out.println("Stored Data: " + userData.toString());
        }
    }
}
