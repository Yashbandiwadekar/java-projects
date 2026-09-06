public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double openingBalance, double overdraftLimit) {
        super(accountNumber, holderName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    // Current accounts may go negative up to the overdraft limit
    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance - amount < -overdraftLimit) {
            throw new InsufficientFundsException(
                    "Withdrawal denied: exceeds overdraft limit of " + overdraftLimit);
        }
        balance -= amount;
    }

    @Override
    public String describeAccountType() {
        return "Current";
    }
}
