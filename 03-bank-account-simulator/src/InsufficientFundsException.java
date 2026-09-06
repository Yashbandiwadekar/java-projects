// Custom checked exception: thrown when a withdrawal/transfer exceeds the balance
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
