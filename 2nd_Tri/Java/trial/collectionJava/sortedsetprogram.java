import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;

public class sortedsetprogram {
    public static void main(String[] args) {

        // ======== PART 1: Adding Students to Exam Enrollment ========

        SortedSet<Integer> enrolledStudents = new TreeSet<>();
        enrolledStudents.add(101);
        enrolledStudents.add(102);
        enrolledStudents.add(102);   // duplicate ignored
        enrolledStudents.add(103);

        SortedSet<Integer> newEnrollments = new TreeSet<>();
        newEnrollments.add(104);
        newEnrollments.add(105);

        enrolledStudents.addAll(newEnrollments);
        System.out.println("Students after enrollment: " + enrolledStudents);

        // Remove the lowest student ID
        Integer firstStudent = enrolledStudents.first();
        enrolledStudents.remove(firstStudent);
        System.out.println("After removing first student (" + firstStudent + "): " + enrolledStudents);


        // ======== Get Highest Student ID ========
        Integer lastStudent = enrolledStudents.last();
        System.out.println("Highest (last) student ID: " + lastStudent);


        // ======== Get Range of Students ========
        // Get subset of students between 102 and 105 (102 inclusive, 105 exclusive)
        SortedSet<Integer> studentRange = enrolledStudents.subSet(102, 105);
        System.out.println("Students between 102 and 105: " + studentRange);


        // ======== PART 2: Managing Exam Subjects ========

        SortedSet<String> subjects = new TreeSet<>();
        subjects.add("Mathematics");
        subjects.add("Physics");
        subjects.add("Chemistry");
        subjects.add("Computer Science");

        System.out.println("\nInitial Subjects (SortedSet): " + subjects);

        subjects.remove("Chemistry");
        System.out.println("After dropping Chemistry: " + subjects);

        Set<String> subjectsToDrop = new HashSet<>();
        subjectsToDrop.add("Mathematics");
        subjectsToDrop.add("Physics");

        subjects.removeAll(subjectsToDrop);
        System.out.println("After removing multiple subjects: " + subjects);
    }
}