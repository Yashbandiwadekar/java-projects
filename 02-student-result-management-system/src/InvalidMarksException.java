// Custom checked exception: validation failure when marks fall outside 0-100
public class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}
