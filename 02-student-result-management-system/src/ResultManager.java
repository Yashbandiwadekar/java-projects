import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
}
