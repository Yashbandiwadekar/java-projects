# Student Result Management System

Console-based Java application to record subject-wise marks for students, compute totals/percentage/grade, and rank students by performance.

## Concepts demonstrated
- **Validation via custom exception** — `InvalidMarksException` rejects marks outside the 0-100 range
- **Collections** — `LinkedHashMap<String, Integer>` per student to keep subjects in insertion order; `ArrayList<Student>` in `ResultManager`
- **Sorting with a Comparator** — `getRanking()` sorts students by percentage, highest first, using `Comparator.comparingDouble(...).reversed()`
- **Method references** — `Student::getPercentage` passed directly into the comparator
- **Class statistics** — class-wide average percentage, plus per-subject average and topper, computed across all students via `getAllSubjects()` / `getSubjectAverage()` / `getTopperForSubject()`

## Run it
```bash
cd src
javac *.java
java StudentResultManagementSystem
```
