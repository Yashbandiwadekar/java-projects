// Base class: demonstrates encapsulation (private fields + public getters/setters)
public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean issued;

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

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    // Subclasses override this to change issue behavior (polymorphism)
    public String getType() {
        return "General";
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author + " | " + getType()
                + " | " + (issued ? "Issued" : "Available");
    }
}
