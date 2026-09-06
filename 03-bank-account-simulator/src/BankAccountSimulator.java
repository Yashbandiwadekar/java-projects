import java.util.Scanner;

public class BankAccountSimulator {

    public static void main(String[] args) {
        Bank bank = new Bank();
        seedData(bank);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> {
                    for (Account a : bank.getAllAccounts()) {
                        System.out.println(a);
                    }
                }
                case 2 -> {
                    System.out.print("Enter account number: ");
                    String accNo = scanner.next();
                    System.out.print("Enter amount to deposit: ");
                    double amount = readDouble(scanner);
                    try {
                        bank.deposit(accNo, amount);
                        System.out.println("Deposited successfully.");
                    } catch (InsufficientFundsException e) {
                        System.out.println("Failed: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Enter account number: ");
                    String accNo = scanner.next();
                    System.out.print("Enter amount to withdraw: ");
                    double amount = readDouble(scanner);
                    try {
                        bank.withdraw(accNo, amount);
                        System.out.println("Withdrawn successfully.");
                    } catch (InsufficientFundsException e) {
                        System.out.println("Failed: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("From account: ");
                    String from = scanner.next();
                    System.out.print("To account: ");
                    String to = scanner.next();
                    System.out.print("Amount: ");
                    double amount = readDouble(scanner);
                    try {
                        bank.transfer(from, to, amount);
                        System.out.println("Transfer successful.");
                    } catch (InsufficientFundsException e) {
                        System.out.println("Transfer failed: " + e.getMessage());
                    }
                }
                case 5 -> running = false;
                default -> System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting Bank Account Simulator.");
    }

    private static void seedData(Bank bank) {
        bank.addAccount(new SavingsAccount("SB001", "Yash", 5000));
        bank.addAccount(new CurrentAccount("CA001", "Priya", 2000, 10000));
    }

    private static void printMenu() {
        System.out.println("\n--- Bank Account Simulator ---");
        System.out.println("1. View all accounts");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double readDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
