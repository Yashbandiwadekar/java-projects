import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library();
        seedData(library);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> {
                    for (Book b : library.getAllBooks()) {
                        System.out.println(b);
                    }
                }
                case 2 -> {
                    for (Member m : library.getAllMembers()) {
                        System.out.println(m);
                    }
                }
                case 3 -> {
                    System.out.print("Enter book ID: ");
                    int bookId = readInt(scanner);
                    System.out.print("Enter member ID: ");
                    int memberId = readInt(scanner);
                    try {
                        library.issueBook(bookId, memberId);
                        System.out.println("Book issued successfully.");
                    } catch (BookNotAvailableException e) {
                        System.out.println("Could not issue book: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("Enter book ID: ");
                    int bookId = readInt(scanner);
                    System.out.print("Enter member ID: ");
                    int memberId = readInt(scanner);
                    try {
                        library.returnBook(bookId, memberId);
                        System.out.println("Book returned successfully.");
                    } catch (BookNotAvailableException e) {
                        System.out.println("Could not return book: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.next();
                    List<Book> results = library.searchByTitle(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No books matched.");
                    } else {
                        for (Book b : results) {
                            System.out.println(b);
                        }
                    }
                }
                case 6 -> running = false;
                default -> System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting Library Management System.");
    }

    private static void seedData(Library library) {
        library.addBook(new Book(1, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(2, "Clean Code", "Robert C. Martin"));
        library.addBook(new ReferenceBook(3, "Java Language Specification", "Oracle"));

        library.addMember(new Member(101, "Yash"));
        library.addMember(new Member(102, "Priya"));
    }

    private static void printMenu() {
        System.out.println("\n--- Library Management System ---");
        System.out.println("1. View all books");
        System.out.println("2. View all members");
        System.out.println("3. Issue a book");
        System.out.println("4. Return a book");
        System.out.println("5. Search book by title");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
