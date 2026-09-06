import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private int rollNumber;
    private String name;
    // LinkedHashMap: preserves the order subjects were added, unlike HashMap
    private Map<String, Integer> marksBySubject = new LinkedHashMap<>();

    public Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public void addMark(String subject, int score) throws InvalidMarksException {
        if (score < 0 || score > 100) {
            throw new InvalidMarksException("Marks for " + subject + " must be between 0 and 100, got " + score);
        }
        marksBySubject.put(subject, score);
    }

    public int getTotal() {
        int total = 0;
        for (int score : marksBySubject.values()) {
            total += score;
        }
        return total;
    }

    public double getPercentage() {
        if (marksBySubject.isEmpty()) {
            return 0.0;
        }
        return (double) getTotal() / marksBySubject.size();
    }

    public String getGrade() {
        double pct = getPercentage();
        if (pct >= 90) return "A+";
        if (pct >= 75) return "A";
        if (pct >= 60) return "B";
        if (pct >= 40) return "C";
        return "F";
    }

    public Map<String, Integer> getMarksBySubject() {
        return marksBySubject;
    }

    @Override
    public String toString() {
        return String.format("%d | %-15s | Total: %-4d | %% : %-6.2f | Grade: %s",
                rollNumber, name, getTotal(), getPercentage(), getGrade());
    }
}
