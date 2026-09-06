import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Abstract class: cannot be instantiated directly, forces subclasses to define their own type
public abstract class Account {
    private String accountNumber;
    private String holderName;
    protected double balance;
    private List<Transaction> history = new ArrayList<>();

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
        deposit(amount, "Deposit");
    }

    public void deposit(double amount, String description) {
        balance += amount;
        recordTransaction(TransactionType.DEPOSIT, amount, description);
    }

    // Template method: the recording step is the same for every account type,
    // only the rule that decides whether a withdrawal is allowed differs.
    public final void withdraw(double amount) throws InsufficientFundsException {
        withdraw(amount, "Withdrawal");
    }

    public final void withdraw(double amount, String description) throws InsufficientFundsException {
        applyWithdrawalRule(amount);
        recordTransaction(TransactionType.WITHDRAWAL, amount, description);
    }

    // Each account type enforces its own withdrawal rule and mutates balance here
    protected abstract void applyWithdrawalRule(double amount) throws InsufficientFundsException;

    public abstract String describeAccountType();

    private void recordTransaction(TransactionType type, double amount, String description) {
        history.add(new Transaction(type, amount, balance, description));
    }

    public List<Transaction> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public String toString() {
        return String.format("%s | %-12s | %-15s | Balance: %.2f",
                accountNumber, describeAccountType(), holderName, balance);
    }
}
