public class BankAccount {
    private double balance;
    private String pin;
    private String[] transactions;
    private int transactionCount;

    public BankAccount(String pin, double initialBalance, int maxTransactions) {
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new String[maxTransactions];
        this.transactionCount = 0;
    }

    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a positive value.");
            return false;
        }
        balance += amount;
        addTransaction("Deposited: $" + amount + " | Balance: $" + balance);
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a positive value.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance! Current balance: $" + balance);
            return false;
        }
        balance -= amount;
        addTransaction("Withdrew: $" + amount + " | Balance: $" + balance);
        return true;
    }

    private void addTransaction(String record) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount] = record;
            transactionCount++;
        } else {
            System.out.println("Transaction history is full (cannot store more records).");
        }
    }

    public void printTransactions() {
        System.out.println("\n--- Transaction History ---");
        if (transactionCount == 0) {
            System.out.println("No transactions found.");
            return;
        }
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
    }
}