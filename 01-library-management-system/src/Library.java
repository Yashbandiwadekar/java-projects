import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    // HashMap keyed by id -> O(1) lookup by book/member id instead of scanning a list
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void issueBook(int bookId, int memberId) throws BookNotAvailableException {
        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book == null) {
            throw new BookNotAvailableException("No book found with ID " + bookId);
        }
        if (member == null) {
            throw new BookNotAvailableException("No member found with ID " + memberId);
        }
        if (book instanceof ReferenceBook) {
            throw new BookNotAvailableException("'" + book.getTitle() + "' is a reference book and cannot be issued");
        }
        if (book.isIssued()) {
            throw new BookNotAvailableException("'" + book.getTitle() + "' is already issued");
        }

        book.setIssued(true);
        member.getIssuedBooks().add(book);
    }

    public void returnBook(int bookId, int memberId) throws BookNotAvailableException {
        Member member = members.get(memberId);
        if (member == null) {
            throw new BookNotAvailableException("No member found with ID " + memberId);
        }

        Book toReturn = null;
        for (Book b : member.getIssuedBooks()) {
            if (b.getBookId() == bookId) {
                toReturn = b;
                break;
            }
        }

        if (toReturn == null) {
            throw new BookNotAvailableException("Member " + member.getName() + " has not issued book ID " + bookId);
        }

        toReturn.setIssued(false);
        member.getIssuedBooks().remove(toReturn);
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Collection<Member> getAllMembers() {
        return members.values();
    }
}
