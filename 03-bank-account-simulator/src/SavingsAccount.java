public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500.0;
    private final double annualInterestRate;

    public SavingsAccount(String accountNumber, String holderName, double openingBalance) {
        this(accountNumber, holderName, openingBalance, 4.0);
    }

    public SavingsAccount(String accountNumber, String holderName, double openingBalance, double annualInterestRate) {
        super(accountNumber, holderName, openingBalance);
        this.annualInterestRate = annualInterestRate;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    // Simple monthly interest: annual rate / 12, applied to the current balance
    public double applyMonthlyInterest() {
        double interest = balance * (annualInterestRate / 12 / 100);
        deposit(interest, String.format("Monthly interest (%.2f%% p.a.)", annualInterestRate));
        return interest;
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

    @Override
    public String toString() {
        return super.toString() + String.format(" | Interest: %.2f%% p.a.", annualInterestRate);
    }
}
