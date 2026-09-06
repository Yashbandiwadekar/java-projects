import java.util.List;
import java.util.Scanner;

public class StudentResultManagementSystem {

    public static void main(String[] args) {
        ResultManager manager = new ResultManager();
        seedData(manager);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter roll number: ");
                    int roll = readInt(scanner);
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    manager.addStudent(new Student(roll, name));
                    System.out.println("Student added.");
                }
                case 2 -> {
                    System.out.print("Enter roll number: ");
                    int roll = readInt(scanner);
                    Student student = manager.findByRoll(roll);
                    if (student == null) {
                        System.out.println("No student with that roll number.");
                        break;
                    }
                    System.out.print("Enter subject name: ");
                    String subject = scanner.next();
                    System.out.print("Enter marks (0-100): ");
                    int marks = readInt(scanner);
                    try {
                        student.addMark(subject, marks);
                        System.out.println("Marks recorded.");
                    } catch (InvalidMarksException e) {
                        System.out.println("Rejected: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Enter roll number: ");
                    int roll = readInt(scanner);
                    Student student = manager.findByRoll(roll);
                    if (student == null) {
                        System.out.println("No student with that roll number.");
                    } else {
                        System.out.println(student);
                        System.out.println("Subject-wise marks: " + student.getMarksBySubject());
                    }
                }
                case 4 -> {
                    List<Student> ranked = manager.getRanking();
                    int rank = 1;
                    for (Student s : ranked) {
                        System.out.println("Rank " + rank + ": " + s);
                        rank++;
                    }
                }
                case 5 -> running = false;
                default -> System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting Student Result Management System.");
    }

    private static void seedData(ResultManager manager) {
        Student yash = new Student(1, "Yash");
        Student priya = new Student(2, "Priya");
        try {
            yash.addMark("Maths", 88);
            yash.addMark("Science", 91);
            priya.addMark("Maths", 95);
            priya.addMark("Science", 89);
        } catch (InvalidMarksException e) {
            // Won't happen with these fixed seed values, but must still be handled
            System.out.println("Seed data error: " + e.getMessage());
        }
        manager.addStudent(yash);
        manager.addStudent(priya);
    }

    private static void printMenu() {
        System.out.println("\n--- Student Result Management System ---");
        System.out.println("1. Add student");
        System.out.println("2. Add marks for a student");
        System.out.println("3. View one student's result");
        System.out.println("4. View ranking (highest percentage first)");
        System.out.println("5. Exit");
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
