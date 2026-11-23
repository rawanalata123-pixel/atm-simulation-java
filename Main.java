import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount account = new BankAccount("1234", 500.0, 50);
        Customer customer = new Customer("Customer", account);

        int maxAttempts = 3;
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < maxAttempts && !authenticated) {
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.nextLine().trim();
            attempts++;

            if (account.verifyPin(enteredPin)) {
                authenticated = true;
                System.out.println("Login successful! Welcome, " + customer.getName() + ".");
            } else {
                int remaining = maxAttempts - attempts;
                if (remaining > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                } else {
                    System.out.println("Maximum attempts reached. Card blocked / Exiting.");
                }
            }
        }

        if (!authenticated) {
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Current Balance: $" + customer.getAccount().getBalance());
                    break;
                case "2":
                    System.out.print("Enter deposit amount: ");
                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); 
                        if (customer.getAccount().deposit(depositAmount)) {
                            System.out.println("Deposit successful! New balance: $" + customer.getAccount().getBalance());
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine();
                    }
                    break;
                case "3":
                    System.out.print("Enter withdrawal amount: ");
                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();
                        if (customer.getAccount().withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful! New balance: $" + customer.getAccount().getBalance());
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine();
                    }
                    break;
                case "4":
                    customer.getAccount().printTransactions();
                    break;
                case "5":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose from 1 to 5.");
            }
        }

        scanner.close();
    }
}