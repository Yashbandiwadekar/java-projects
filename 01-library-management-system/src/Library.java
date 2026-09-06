import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private static final int LOAN_PERIOD_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0;

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

        book.markIssued(LocalDate.now().plusDays(LOAN_PERIOD_DAYS));
        member.getIssuedBooks().add(book);
    }

    public double returnBook(int bookId, int memberId) throws BookNotAvailableException {
        return returnBook(bookId, memberId, LocalDate.now());
    }

    // returnDate is a parameter (not always "today") so overdue behavior can be tested
    // deterministically instead of waiting for real calendar days to pass.
    public double returnBook(int bookId, int memberId, LocalDate returnDate) throws BookNotAvailableException {
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

        double fine = 0.0;
        if (returnDate.isAfter(toReturn.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(toReturn.getDueDate(), returnDate);
            fine = daysLate * FINE_PER_DAY;
        }

        toReturn.markReturned();
        member.getIssuedBooks().remove(toReturn);
        return fine;
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
