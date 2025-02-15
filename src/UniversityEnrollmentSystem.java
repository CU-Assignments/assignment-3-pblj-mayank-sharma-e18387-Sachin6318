import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private List<Student> enrolledStudents;
    private List<String> prerequisites;

    public Course(String name, int capacity, List<String> prerequisites) {
        this.name = name;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = prerequisites;
    }

    public String getName() {
        return name;
    }

    public void enrollStudent(Student student) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("Course " + name + " is full.");
        }
        for (String prerequisite : prerequisites) {
            if (!student.hasCompletedCourse(prerequisite)) {
                throw new PrerequisiteNotMetException("Student has not completed prerequisite: " + prerequisite);
            }
        }
        enrolledStudents.add(student);
        student.addCompletedCourse(name);
    }
}

class Student {
    private String name;
    private Set<String> completedCourses;

    public Student(String name) {
        this.name = name;
        this.completedCourses = new HashSet<>();
    }

    public boolean hasCompletedCourse(String course) {
        return completedCourses.contains(course);
    }

    public void addCompletedCourse(String course) {
        completedCourses.add(course);
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Student student = new Student("Alice");
        Course course = new Course("Advanced Java", 2, Arrays.asList("Basic Java"));

        try {
            course.enrollStudent(student);
            System.out.println("Enrollment successful!");
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        }
    }
}
