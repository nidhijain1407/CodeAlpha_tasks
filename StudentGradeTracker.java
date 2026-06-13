import java.util.ArrayList;
import java.util.Scanner;

/**
 * Student class representing a student's data.
 */
class Student {
    private int studentId;
    private String studentName;
    private double marks;

    // Constructor
    public Student(int studentId, String studentName, double marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getMarks() {
        return marks;
    }

    // String representation for easy printing
    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + studentName + ", Marks: " + marks;
    }
}

/**
 * Main application class for tracking student grades.
 */
public class StudentGradeTracker {
    private ArrayList<Student> students; // Stores Student objects
    private Scanner scanner;

    // Constructor initializes the list and scanner
    public StudentGradeTracker() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Show the menu options
    private void showMenu() {
        System.out.println("\n--- Student Grade Tracker Menu ---");
        System.out.println("1. Add student details");
        System.out.println("2. Display all students");
        System.out.println("3. Calculate average marks");
        System.out.println("4. Find highest marks");
        System.out.println("5. Find lowest marks");
        System.out.println("6. Exit and show summary report");
        System.out.print("Enter your choice (1-6): ");
    }

    // Add a new student (reads input from user)
    private void addStudent() {
        try {
            System.out.print("Enter student ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter marks (e.g., 85.5): ");
            double marks = Double.parseDouble(scanner.nextLine().trim());

            Student s = new Student(id, name, marks);
            students.add(s);
            System.out.println("Student added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values for ID and marks.");
        }
    }

    // Display all students
    private void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\nList of Students:");
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    // Calculate average marks
    private void calculateAverageMarks() {
        if (students.isEmpty()) {
            System.out.println("No students available to calculate average.");
            return;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getMarks();
        }
        double avg = sum / students.size();
        System.out.printf("Average marks: %.2f%n", avg);
    }

    // Find student(s) with highest marks
    private void findHighestMarks() {
        if (students.isEmpty()) {
            System.out.println("No students available to find highest marks.");
            return;
        }
        double highest = students.get(0).getMarks();
        for (Student s : students) {
            if (s.getMarks() > highest) {
                highest = s.getMarks();
            }
        }
        System.out.printf("Highest marks: %.2f. Student(s) with highest marks:%n", highest);
        for (Student s : students) {
            if (Double.compare(s.getMarks(), highest) == 0) {
                System.out.println(s.toString());
            }
        }
    }

    // Find student(s) with lowest marks
    private void findLowestMarks() {
        if (students.isEmpty()) {
            System.out.println("No students available to find lowest marks.");
            return;
        }
        double lowest = students.get(0).getMarks();
        for (Student s : students) {
            if (s.getMarks() < lowest) {
                lowest = s.getMarks();
            }
        }
        System.out.printf("Lowest marks: %.2f. Student(s) with lowest marks:%n", lowest);
        for (Student s : students) {
            if (Double.compare(s.getMarks(), lowest) == 0) {
                System.out.println(s.toString());
            }
        }
    }

    // Print summary report of all students (called on exit)
    private void printSummaryReport() {
        System.out.println("\n--- Summary Report ---");
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }
        displayAllStudents();
        calculateAverageMarks();
        // Also show highest and lowest for convenience
        findHighestMarks();
        findLowestMarks();
        System.out.println("--- End of Summary ---");
    }

    // Main loop for menu-driven interaction
    public void run() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    displayAllStudents();
                    break;
                case "3":
                    calculateAverageMarks();
                    break;
                case "4":
                    findHighestMarks();
                    break;
                case "5":
                    findLowestMarks();
                    break;
                case "6":
                    exit = true;
                    printSummaryReport();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }
        }
        // Close resources
        scanner.close();
        System.out.println("Application exited. Goodbye!");
    }

    // Entry point
    public static void main(String[] args) {
        StudentGradeTracker app = new StudentGradeTracker();
        app.run();
    }
}
