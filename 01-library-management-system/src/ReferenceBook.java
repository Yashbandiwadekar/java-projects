// Inheritance: ReferenceBook IS-A Book, but can never be issued (library rule)
public class ReferenceBook extends Book {

    public ReferenceBook(int bookId, String title, String author) {
        super(bookId, title, author);
    }

    // Method overriding: changes labeling behavior from the parent class
    @Override
    public String getType() {
        return "Reference (In-library only)";
    }
}
