package org.example;

public class Teacher {
    private final String teacherId;
    private String name;
    private double salary;
    private static int nextId = 1; // Static variable for generating IDs

    // Constructor
    public Teacher(String name, double salary) {
        setName(name);
        setSalary(salary);
        this.teacherId = generateTeacherId();
    }

    // Getter for getTeacherId
    public String getTeacherId() {
        return teacherId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        this.name = name;
    }

    // Getter and Setter for salary
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    // Method to generate teacherId
    private String generateTeacherId() {
        //String id = "T" + nextId;
        String id = "T" + String.format("%03d", nextId);  // %03d formats the number as 3-digits
        nextId++;
        return id;
    }

    // Method to reset nextId (for testing purposes)
    public static void resetNextId() {
        nextId = 1;
    }
}
