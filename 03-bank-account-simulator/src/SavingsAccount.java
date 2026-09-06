public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    // Savings accounts must keep a minimum balance after withdrawal
    @Override
    protected void applyWithdrawalRule(double amount) throws InsufficientFundsException {
        if (balance - amount < MIN_BALANCE) {
            throw new InsufficientFundsException(
                    "Withdrawal denied: savings account must keep a minimum balance of " + MIN_BALANCE);
        }
        balance -= amount;
    }

    @Override
    public String describeAccountType() {
        return "Savings";
    }
}
