// Abstract class: cannot be instantiated directly, forces subclasses to define their own type
public abstract class Account {
    private String accountNumber;
    private String holderName;
    protected double balance;

    public Account(String accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    // Each account type enforces its own withdrawal rule, so this stays abstract here
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public abstract String describeAccountType();

    @Override
    public String toString() {
        return String.format("%s | %-12s | %-15s | Balance: %.2f",
                accountNumber, describeAccountType(), holderName, balance);
    }
}
