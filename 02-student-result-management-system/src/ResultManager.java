import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ResultManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findByRoll(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    // Returns students sorted by percentage, highest first (rank 1 = top of list)
    public List<Student> getRanking() {
        List<Student> ranked = new ArrayList<>(students);
        ranked.sort(Comparator.comparingDouble(Student::getPercentage).reversed());
        return ranked;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public double getClassAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getPercentage();
        }
        return sum / students.size();
    }

    // Every subject name that appears for any student, in the order first seen
    public Set<String> getAllSubjects() {
        Set<String> subjects = new LinkedHashSet<>();
        for (Student s : students) {
            subjects.addAll(s.getMarksBySubject().keySet());
        }
        return subjects;
    }

    public double getSubjectAverage(String subject) {
        int total = 0;
        int count = 0;
        for (Student s : students) {
            Integer marks = s.getMarksBySubject().get(subject);
            if (marks != null) {
                total += marks;
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) total / count;
    }

    public Student getTopperForSubject(String subject) {
        Student topper = null;
        int highest = -1;
        for (Student s : students) {
            Integer marks = s.getMarksBySubject().get(subject);
            if (marks != null && marks > highest) {
                highest = marks;
                topper = s;
            }
        }
        return topper;
    }
}
