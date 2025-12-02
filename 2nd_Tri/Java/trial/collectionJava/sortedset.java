import java.util.*;
public class sortedset {
    public static void main(String[] args) {

        // ======== PART 1: Adding Students to Exam Enrollment ========

        // Sorted set of enrolled students (sorted, unique)
        SortedSet<Integer> enrolledStudents = new TreeSet<>();
        enrolledStudents.add(101);
        enrolledStudents.add(102);
        enrolledStudents.add(102);   // duplicate will be ignored
        enrolledStudents.add(103);

        // New students enrolling later
        SortedSet<Integer> newEnrollments = new TreeSet<>();
        newEnrollments.add(104);
        newEnrollments.add(105);

        // Add new students
        enrolledStudents.addAll(newEnrollments);

        System.out.println("Students after enrollment (TreeSet Sorted): " + enrolledStudents);

        // Remove the lowest student ID (TreeSet-specific)
        Integer firstStudent = enrolledStudents.first();
        enrolledStudents.remove(firstStudent);

        System.out.println("After removing first student (" + firstStudent + "): " + enrolledStudents);


        // ======== PART 2: Managing Exam Subjects ========

        // Sorted Set of subjects (alphabetically sorted, unique)
        SortedSet<String> subjects = new TreeSet<>();
        subjects.add("Mathematics");
        subjects.add("Physics");
        subjects.add("Chemistry");
        subjects.add("Computer Science");

        System.out.println("\nInitial Subjects (SortedSet): " + subjects);

        // Remove a subject
        subjects.remove("Chemistry");
        System.out.println("After dropping Chemistry: " + subjects);

        // Remove multiple subjects
        Set<String> subjectsToDrop = new TreeSet<>();
        subjectsToDrop.add("Mathematics");
        subjectsToDrop.add("Physics");
	}
}


class Items2 {
    int id;
    String name;

    public Items2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Items2{id=" + id + ", name='" + name + "'}";
        

        subjects.removeAll(subjectsToDrop);
        System.out.println("After dropping multiple subjects: " + subjects);
    }
}

class ItemComparator2 implements Comparator<Items2> {
    @Override
    public int compare(Items2 o1, Items2 o2) {
        return Integer.compare(o1.id, o2.id);
    }
}

class ItemsDemo2 {
    public static void main(String[] args) {
        SortedSet<Items2> itemSet = new TreeSet<>(new ItemComparator2());

        itemSet.add(new Items2(3, "Item3"));
        itemSet.add(new Items2(1, "Item1"));
        itemSet.add(new Items2(2, "Item2"));

        System.out.println("\nItems in SortedSet (by id): " + itemSet);
    }
}