import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public boolean hasSlot() {
        return enrolled < capacity;
    }

    public void enroll() {
        if (hasSlot()) {
            enrolled++;
        }
    }

    public void drop() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + "\nDescription: " + description + "\nSchedule: " + schedule + "\nAvailable Slots: " + (capacity - enrolled) + "/" + capacity;
    }
}

class Student {
    String studentId;
    String name;
    List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.hasSlot() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enroll();
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Unable to register for " + course.title + ". No slots available or already registered.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.drop();
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for " + course.title);
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("\nRegistered Courses:");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class CourseManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        Map<String, Student> students = new HashMap<>();

        // Predefined courses
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic programming concepts", 50, "Mon-Wed 10:00-11:30"));
        courses.add(new Course("MATH202", "Calculus II", "Advanced integration techniques", 30, "Tue-Thu 12:00-13:30"));
        courses.add(new Course("PHY303", "Physics III", "Electromagnetism and optics", 40, "Fri 14:00-16:00"));

        System.out.println("Welcome to the Course Management System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register a Student");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Display Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        System.out.println(course);
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.next();
                    students.put(studentId, new Student(studentId, name));
                    System.out.println("Student registered successfully.");
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    Student student = students.get(studentId);
                    if (student != null) {
                        System.out.print("Enter Course Code: ");
                        String courseCode = scanner.next();
                        Course courseToRegister = courses.stream().filter(c -> c.courseCode.equals(courseCode)).findFirst().orElse(null);
                        if (courseToRegister != null) {
                            student.registerCourse(courseToRegister);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    student = students.get(studentId);
                    if (student != null) {
                        System.out.print("Enter Course Code: ");
                        String courseCode = scanner.next();
                        Course courseToDrop = courses.stream().filter(c -> c.courseCode.equals(courseCode)).findFirst().orElse(null);
                        if (courseToDrop != null) {
                            student.dropCourse(courseToDrop);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    student = students.get(studentId);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

