import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String toString() {
        return id + "\t" + name + "\t" + marks;
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: "); // outOff 500
        double marks = sc.nextDouble();

        students.add(new Student(id, name, marks));

        System.out.println("Student Added Successfully!\n");
    }

    public static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.\n");
            return;
        }

        System.out.println("\n--------------------------------");
        System.out.println("ID\tNAME\tMARKS");
        System.out.println("--------------------------------");

        for (Student s : students) {
            System.out.println(s);
        }

        System.out.println();
    }

    public static void calculateAverage() {

        if (students.isEmpty()) {
            System.out.println("No student records found.\n");
            return;
        }

        double sum = 0;

        for (Student s : students) {
            sum += s.getMarks();
        }

        double average = sum / students.size();

        System.out.printf("Average Marks: %.2f\n", average);
    }

    public static void highestMarks() {

        if (students.isEmpty()) {
            System.out.println("No student records found.\n");
            return;
        }

        Student highest = students.get(0);

        for (Student s : students) {
            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }
        }

        System.out.println("Highest Scorer:");
        System.out.println("Name: " + highest.getName());
        System.out.println("Marks: " + highest.getMarks());
        System.out.println();
    }

    public static void lowestMarks() {

        if (students.isEmpty()) {
            System.out.println("No student records found.\n");
            return;
        }

        Student lowest = students.get(0);

        for (Student s : students) {
            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }

        System.out.println("Lowest Scorer:");
        System.out.println("Name: " + lowest.getName());
        System.out.println("Marks: " + lowest.getMarks());
        System.out.println();
    }

    public static void summaryReport() {

        if (students.isEmpty()) {
            System.out.println("No student records found.\n");
            return;
        }

        double total = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student s : students) {

            total += s.getMarks();

            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }

            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }

        double average = total / students.size();

        System.out.println("\n========== SUMMARY REPORT ==========");
        System.out.println("Total Students : " + students.size());
        System.out.printf("Average Marks  : %.2f\n", average);
        System.out.println("Highest Marks  : " + highest.getMarks()
                + " (" + highest.getName() + ")");
        System.out.println("Lowest Marks   : " + lowest.getMarks()
                + " (" + lowest.getName() + ")");
        System.out.println("====================================\n");
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Calculate Average");
            System.out.println("4. Highest Marks");
            System.out.println("5. Lowest Marks");
            System.out.println("6. Summary Report");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    calculateAverage();
                    break;

                case 4:
                    highestMarks();
                    break;

                case 5:
                    lowestMarks();
                    break;

                case 6:
                    summaryReport();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!\n");
            }

        } while (choice != 7);
    }
}
