import java.time.LocalDate;

// Base class: demonstrates encapsulation (private fields + public getters/setters)
public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean issued;
    private LocalDate dueDate;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void markIssued(LocalDate dueDate) {
        this.issued = true;
        this.dueDate = dueDate;
    }

    public void markReturned() {
        this.issued = false;
        this.dueDate = null;
    }

    // Subclasses override this to change issue behavior (polymorphism)
    public String getType() {
        return "General";
    }

    @Override
    public String toString() {
        String status = issued ? "Issued (due " + dueDate + ")" : "Available";
        return bookId + " | " + title + " | " + author + " | " + getType() + " | " + status;
    }
}
