// Custom checked exception: demonstrates exception handling beyond built-in types
public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
