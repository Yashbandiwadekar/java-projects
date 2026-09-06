import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, double amount) throws InsufficientFundsException {
        Account account = requireAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws InsufficientFundsException {
        Account account = requireAccount(accountNumber);
        account.withdraw(amount);
    }

    // Transfer only debits the source once the withdrawal succeeds, so a failed
    // withdrawal never leaves money "created" in the destination account.
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws InsufficientFundsException {
        Account from = requireAccount(fromAccountNumber);
        Account to = requireAccount(toAccountNumber);

        from.withdraw(amount, "Transfer to " + to.getAccountNumber());
        to.deposit(amount, "Transfer from " + from.getAccountNumber());
    }

    // Credits monthly interest to every savings account; other account types are untouched
    public void applyMonthlyInterestToSavings() {
        for (Account account : accounts.values()) {
            if (account instanceof SavingsAccount savings) {
                savings.applyMonthlyInterest();
            }
        }
    }

    private Account requireAccount(String accountNumber) throws InsufficientFundsException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new InsufficientFundsException("No account found with number " + accountNumber);
        }
        return account;
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
}
