// // ────────────────────────────────
// // Customer class demonstrating Access Specifiers
// // ────────────────────────────────
// class Customer {
//     // private fields (not accessible outside directly)
//     private String accountNumber;
//     private double balance;
//     private String accountHolderName;
//     private String accountHolder; // previously public
//     protected String bankName;

//     // constructor
//     public Customer(String accountNumber, String accountHolderName, double initialBalance, String accountHolder, String bankName) {
//         this.accountNumber = accountNumber;
//         this.accountHolderName = accountHolderName;
//         this.balance = initialBalance;
//         this.accountHolder = accountHolder;
//         this.bankName = bankName;
//     }

//     // private method — displays sensitive info (like password in teacher example)
//     private void displaySensitive() {
//         System.out.println("Account Number (Private): " + accountNumber);
//         System.out.println("Account Holder Name (Private): " + accountHolderName);
//     }

//     // public method — can be called outside, but internally calls the private method
//     public void showSensitiveDetails() {
//         displaySensitive();
//     }

//     // public method to show only public / allowed info
//     public void displayPublicDetails() {
//         System.out.println("Bank Name (Protected): " + bankName);
//         System.out.println("Account Holder (Private): " + accountHolder);
//         System.out.println("Balance: " + balance);
//     }

//     // deposit & withdraw
//     public void deposit(double amount) {
//         if (amount > 0) {
//             balance += amount;
//             System.out.println("Deposited: " + amount);
//         } else {
//             System.out.println("Deposit amount must be positive.");
//         }
//     }

//     public void withdraw(double amount) {
//         if (amount > 0 && amount <= balance) {
//             balance -= amount;
//             System.out.println("Withdrew: " + amount);
//         } else {
//             System.out.println("Invalid withdrawal amount.");
//         }
//     }

//     // public setter for account holder
//     public void setAccountHolder(String name) {
//         this.accountHolder = name;
//     }

//     // public getter for account holder
//     public String getAccountHolder() {
//         return this.accountHolder;
//     }
// }

// // ────────────────────────────────
// // Main class
// // ────────────────────────────────
// public class account {
//     public static void main(String[] args) {
//         // creating object
//         Customer cust = new Customer("123456789", "John Doe", 1000.0, "John Doe", "Bank of Java");

//         // show only public allowed details
//         cust.displayPublicDetails();
//         cust.deposit(500.0);
//         cust.withdraw(200.0);
//         cust.displayPublicDetails();

//         // changing account holder using setter
//         cust.setAccountHolder("Shayam");
//         System.out.println("Updated Account Holder (via setter): " + cust.getAccountHolder());
//         cust.displayPublicDetails();

//         // calling private method via public method (like teacher’s showpassword)
//         cust.showSensitiveDetails();
//     }
// }
