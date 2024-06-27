package src;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    // Class variables
    private String courseId; // Unique ID for the course
    private String name; // Name of the course
    private double price; // Price of the course
    private double moneyEarned; // Total money earned by the course
    private Teacher teacher; // Teacher assigned to the course
    private List<Student> enrolledStudents; // List of students enrolled in the course

    // Parameterized constructor
    public Course(String name, double price) {
        this.courseId = UUID.randomUUID().toString(); // Auto-generate unique ID
        this.name = name;
        this.price = price;
        this.moneyEarned = 0; // Initially, no money earned
        this.teacher = null; // Initially, no teacher assigned
        this.enrolledStudents = new ArrayList<>(); // Initialize the list of enrolled students
    }

    // Getter methods
    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getMoneyEarned() {
        return moneyEarned;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Method to enroll a student in the course
    public void enrollStudent(Student student) {
        if (!this.enrolledStudents.contains(student)) { // Check if student is not already enrolled
            this.enrolledStudents.add(student); // Add student to the list
            this.moneyEarned += this.price; // Update money earned based on course price
            student.setCourse(this); // Associate the student with this course
        }
    }

    // Method to unenroll a student from the course
    public void unenrollStudent(Student student) {
        if (this.enrolledStudents.contains(student)) { // Check if student is enrolled
            this.enrolledStudents.remove(student); // Remove student from the list
            this.moneyEarned -= this.price; // Adjust money earned if student unenrolls
            student.setCourse(null); // Disassociate the student from this course
        }
    }

    // Method to calculate profit
    public double calculateProfit() {
        double totalTeacherSalaries = (this.teacher != null) ? this.teacher.getSalary() : 0; // Get teacher's salary or 0 if no teacher
        return this.moneyEarned - totalTeacherSalaries; // Calculate profit
    }

    // Method to display course details
    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", moneyEarned=" + moneyEarned +
                ", teacher=" + (teacher != null ? teacher.getName() : "None") + // Display teacher's name or "None" if no teacher
                ", enrolledStudents=" + enrolledStudents.size() + // Display number of enrolled students
                '}';
    }
}
