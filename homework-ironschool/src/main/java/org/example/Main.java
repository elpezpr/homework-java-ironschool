package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTeachers = getPositiveInteger(scanner, "How many teachers should be created?");
        for (int i = 0; i < numberOfTeachers; i++) {
            System.out.println("Enter name of teacher " + (i + 1) + ":");
            String name = scanner.nextLine();
            double salary = getPositiveDouble(scanner, "Enter salary of teacher " + (i + 1) + ":");
            teachers.add(new Teacher(name, salary));
        }

        int numberOfCourses = getPositiveInteger(scanner, "How many courses should be created?");
        for (int i = 0; i < numberOfCourses; i++) {
            System.out.println("Enter name of course " + (i + 1) + ":");
            String name = scanner.nextLine();
            double price = getPositiveDouble(scanner, "Enter price of course " + (i + 1) + ":");
            courses.add(new Course(name, price));
        }

        int numberOfStudents = getPositiveInteger(scanner, "How many students should be created?");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Enter name of student " + (i + 1) + ":");
            String name = scanner.nextLine();
            System.out.println("Enter address of student " + (i + 1) + ":");
            String address = scanner.nextLine();
            System.out.println("Enter email of student " + (i + 1) + ":");
            String email = scanner.nextLine();
            students.add(new Student(name, address, email));
        }

        // Command loop
        while (true) {
            System.out.println("Enter a command:");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            processCommand(command);
        }

        scanner.close();
    }

    private static int getPositiveInteger(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.println(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private static double getPositiveDouble(Scanner scanner, String prompt) {
        double number;
        while (true) {
            System.out.println(prompt);
            try {
                number = Double.parseDouble(scanner.nextLine());
                if (number > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private static void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            System.out.println("Invalid command");
            return;
        }

        switch (parts[0].toUpperCase()) {
            case "ENROLL":
                if (parts.length == 3) {
                    enrollStudent(parts[1], parts[2]);
                } else {
                    System.out.println("Usage: ENROLL [STUDENT_ID] [COURSE_ID]");
                }
                break;
            case "ASSIGN":
                if (parts.length == 3) {
                    assignTeacher(parts[1], parts[2]);
                } else {
                    System.out.println("Usage: ASSIGN [TEACHER_ID] [COURSE_ID]");
                }
                break;
            case "SHOW":
                if (parts.length == 2) {
                    if (parts[1].equalsIgnoreCase("COURSES")) {
                        showCourses();
                    } else if (parts[1].equalsIgnoreCase("STUDENTS")) {
                        showStudents();
                    } else if (parts[1].equalsIgnoreCase("TEACHERS")) {
                        showTeachers();
                    } else if (parts[1].equalsIgnoreCase("PROFIT")) {
                        showProfit();
                    } else {
                        System.out.println("Unknown SHOW command");
                    }
                } else {
                    System.out.println("Usage: SHOW [COURSES|STUDENTS|TEACHERS|PROFIT]");
                }
                break;
            case "LOOKUP":
                if (parts.length == 3) {
                    if (parts[1].equalsIgnoreCase("COURSE")) {
                        lookupCourse(parts[2]);
                    } else if (parts[1].equalsIgnoreCase("STUDENT")) {
                        lookupStudent(parts[2]);
                    } else if (parts[1].equalsIgnoreCase("TEACHER")) {
                        lookupTeacher(parts[2]);
                    } else {
                        System.out.println("Unknown LOOKUP command");
                    }
                } else {
                    System.out.println("Usage: LOOKUP [COURSE|STUDENT|TEACHER] [ID]");
                }
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private static void enrollStudent(String studentId, String courseId) {
        Student student = students.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst().orElse(null);
        Course course = courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);

        if (student != null && course != null) {
            student.setCourse(course);
            course.setMoneyEarned(course.getPrice()); // Changed from addMoneyEarned to setMoneyEarned
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Invalid student or course ID.");
        }
    }

    private static void assignTeacher(String teacherId, String courseId) {
        Teacher teacher = teachers.stream().filter(t -> t.getTeacherId().equals(teacherId)).findFirst().orElse(null);
        Course course = courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);

        if (teacher != null && course != null) {
            course.setTeacher(teacher);
            System.out.println("Teacher assigned successfully.");
        } else {
            System.out.println("Invalid teacher or course ID.");
        }
    }

    private static void showCourses() {
        courses.forEach(course -> System.out.println(course.getCourseId() + ":" + course.getName()));
    }

    private static void lookupCourse(String courseId) {
        Course course = courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);
        if (course != null) {
            System.out.println("Course ID: " + course.getCourseId());
            System.out.println("Name: " + course.getName());
            System.out.println("Price: " + course.getPrice());
            System.out.println("Money Earned: " + course.getMoneyEarned());
            System.out.println("Teacher: " + (course.getTeacher() != null ? course.getTeacher().getName() : "None"));
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void showStudents() {
        students.forEach(student -> System.out.println(student.getStudentId() + ":" + student.getName()));
    }

    private static void lookupStudent(String studentId) {
        Student student = students.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst().orElse(null);
        if (student != null) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + (student.getCourse() != null ? student.getCourse().getName() : "None"));
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void showTeachers() {
        teachers.forEach(teacher -> System.out.println(teacher.getTeacherId() + ":" + teacher.getName()));
    }

    private static void lookupTeacher(String teacherId) {
        Teacher teacher = teachers.stream().filter(t -> t.getTeacherId().equals(teacherId)).findFirst().orElse(null);
        if (teacher != null) {
            System.out.println("Teacher ID: " + teacher.getTeacherId());
            System.out.println("Name: " + teacher.getName());
            System.out.println("Salary: " + teacher.getSalary());
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private static void showProfit() {
        double totalMoneyEarned = courses.stream().mapToDouble(Course::getMoneyEarned).sum();
        double totalSalaries = teachers.stream().mapToDouble(Teacher::getSalary).sum();
        double profit = totalMoneyEarned - totalSalaries;
        System.out.println("Profit: " + profit);
    }
}
