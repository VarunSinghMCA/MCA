import java.util.*;

public class queue {

    public static void main(String[] args) {

        // ======== PART 1: Adding Students to Exam Enrollment ========

        // Queue of enrolled students (FIFO order)
        Queue<Integer> enrolledStudents = new LinkedList<>();
        enrolledStudents.add(101);
        enrolledStudents.add(102);
        enrolledStudents.add(102);
        enrolledStudents.add(103);

        // New students enrolling later
        Queue<Integer> newEnrollments = new LinkedList<>();
        newEnrollments.offer(104);
        newEnrollments.offer(105);

        // Add all new enrollments into main queue
        enrolledStudents.addAll(newEnrollments);

        System.out.println("Students in queue after enrollment: " + enrolledStudents);

        // Removing first student processed for exam (Queue operation)
        Integer firstStudent = enrolledStudents.poll();
        System.out.println("First student processed (poll): " + firstStudent);
        System.out.println("Queue after processing first student: " + enrolledStudents);


        // ======== PART 2: Managing Exam Subjects ========

        // Queue of subjects registered
        Queue<String> subjects = new LinkedList<>();
        subjects.offer("Mathematics");
        subjects.offer("Physics");
        subjects.offer("Chemistry");
        subjects.offer("Computer Science");

        System.out.println("\nInitial Subjects Queue: " + subjects);

        // Student drops a subject
        subjects.remove("Chemistry");
        System.out.println("After dropping Chemistry: " + subjects);

        // Student drops multiple subjects
        List<String> subjectsToDrop = new ArrayList<>();
        subjectsToDrop.add("Mathematics");
        subjectsToDrop.add("Physics");

        subjects.removeAll(subjectsToDrop);
        System.out.println("After dropping multiple subjects: " + subjects);

        // Process next subject (demonstrate poll)
        String nextSubject = subjects.poll();
        System.out.println("Next subject processed (poll): " + nextSubject);
        System.out.println("Final Subjects Queue: " + subjects);
    }
}