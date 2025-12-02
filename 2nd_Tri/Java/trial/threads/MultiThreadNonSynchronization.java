class BankAccount {
    private int balance = 1000; // Initial balance

    // Method to deposit money
    public void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
        balance += amount;
        System.out.println("Balance after deposit: " + balance);
    }

    // Method to withdraw money
    public void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            balance -= amount;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + " but insufficient balance!");
        }
    }

    // Method to get the balance
    public int getBalance() {
        return balance;
    }
}

class CustomerThread extends Thread {
    private BankAccount account;

    public CustomerThread(BankAccount account, String name) {
        super(name);
        this.account = account;
    }

   
    public void run() {
        account.deposit(500); // Deposit 500
        account.withdraw(700); // Withdraw 700
    }
}

public class MultiThreadNonSynchronization {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(); // Shared account object

        // Create customer threads
        CustomerThread customer1 = new CustomerThread(account, "Customer1");
        CustomerThread customer2 = new CustomerThread(account, "Customer2");

        // Start threads
        customer1.start();
        customer2.start();
    }
}