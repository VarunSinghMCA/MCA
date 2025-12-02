import java.util.*;

public class linkedlist {

    public static void main(String[] args) {

        // ======== PART 1: Adding Students to Exam Enrollment ========

        // Main list of enrolled students (by ID) - Using LinkedList
        List<Integer> enrolledStudents = new LinkedList<>();
        enrolledStudents.add(101);
        enrolledStudents.add(102);
        enrolledStudents.add(102);
        enrolledStudents.add(103);

        // New students enrolling later
        List<Integer> newEnrollments = new LinkedList<>();
        newEnrollments.add(104);
        newEnrollments.add(105);

        // Add new students to the main enrollment list
        enrolledStudents.addAll(newEnrollments);

        System.out.println("Students after enrollment (add & addAll): " + enrolledStudents);


        // ======== PART 2: Managing Exam Subjects ========

        // Subjects a student initially registered for - Using LinkedList
        List<String> subjects = new LinkedList<>();
        subjects.add("Mathematics");
        subjects.add("Physics");
        subjects.add("Chemistry");
        subjects.add("Computer Science");

        System.out.println("\nInitial Subjects Registered: " + subjects);

        // Student drops a subject
        subjects.remove("Chemistry");
        System.out.println("After dropping Chemistry: " + subjects);

        // Student drops multiple subjects together
        List<String> subjectsToDrop = new LinkedList<>();
        subjectsToDrop.add("Mathematics");
        subjectsToDrop.add("Physics");

        subjects.removeAll(subjectsToDrop);
        System.out.println("After dropping multiple subjects: " + subjects);
    }
}
