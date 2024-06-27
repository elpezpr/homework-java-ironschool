package org.example;

import java.util.*;

public class Main implements ICommands {

    private static Map<String, Teacher> teachersList = new HashMap<>();
    private static Map<String, Course> coursesList = new HashMap<>();
    private static Map<String, Student> studentsList = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scannerIn = new Scanner(System.in);

        System.out.println("Please enter name to create a School");
        String schoolName = scannerIn.nextLine();
        System.out.println("How many teachers?");
        int numberOfTeachers = scannerIn.nextInt();
        scannerIn.nextLine();

        for (int i = 0; i < numberOfTeachers; i++) {
            System.out.println("Enter teacher" + (i + 1) + " name");
            String teacherName = scannerIn.nextLine();
            System.out.println("Enter " + teacherName + "'s salary");
            double teacherSalary = scannerIn.nextDouble();
            scannerIn.nextLine();

            Teacher teacher = new Teacher(teacherName, teacherSalary);
            teachersList.put(teacher.getName(), teacher);
        }
        System.out.println(teachersList);

        System.out.println("Enter the amount of courses");
        int numberOfCourses = scannerIn.nextInt();
        scannerIn.nextLine();

        for (int i = 0; i < numberOfCourses; i++) {
            System.out.println("Enter course" + (i + 1) + " name");
            String courseName = scannerIn.nextLine().trim().toLowerCase();
            System.out.println("Enter course price");
            double coursePrice = scannerIn.nextDouble();
            scannerIn.nextLine();

            Course course = new Course(courseName, coursePrice);
            coursesList.put(course.getName(), course);
        }

        System.out.println(coursesList);

        System.out.println("Enter how many students");
        int numberOfStudents = scannerIn.nextInt();
        scannerIn.nextLine();

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Enter student's name");
            String name = scannerIn.nextLine();
            System.out.println("Enter student's address");
            String address = scannerIn.nextLine();
            System.out.println("Enter student's email");
            String email = scannerIn.nextLine();

            Student student = new Student(name, address, email);
            studentsList.put(student.getName(), student);
        }

        System.out.println(studentsList);

        while (true) {
            System.out.println("""
                Enter a command:
                1) ENROLL a student to a course
                2) ASSIGN a teacher to a course
                3) SHOW COURSES
                4) LOOKUP COURSE
                5) SHOW STUDENTS
                6) LOOKUP STUDENT
                7) SHOW TEACHERS
                8) LOOKUP TEACHER
                9) SHOW PROFIT
                10) EXIT
                """);

            do{
                //this we did in class
                scannerIn = new Scanner(System.in);
                System.out.print("Enter a command: ");
            }
            while(!scannerIn.hasNextInt());

            int option = scannerIn.nextInt();
            scannerIn.nextLine();
            String name;
            String course;

            switch (option) {
                case 1:
                    System.out.println("Enter student name:");
                    name = scannerIn.nextLine();
                    System.out.println("Enter course name:");
                    course = scannerIn.nextLine();
                    main.enroll(name, course);
                    break;
                case 2:
                    System.out.println("Enter teacher name:");
                    name = scannerIn.nextLine();
                    System.out.println("Enter course name:");
                    course = scannerIn.nextLine();
                    main.assign(name, course);
                    break;
                case 3:
                    main.showCourses();
                    break;
                case 4:
                    System.out.println("Enter course name:");
                    course = scannerIn.nextLine();
                    try{
                        main.lookUpCourse(course);
                    } catch (InputMismatchException e){
                        System.out.println("Name is not properly formatted: "+ course);
                    }

                    break;
                case 5:
                    main.showStudents();
                    break;
                case 6:
                    System.out.println("Enter student name:");
                    name = scannerIn.nextLine();
                    main.lookUpStudent(name);
                    break;
                case 7:
                    main.showTeachers();
                    break;
                case 8:
                    System.out.println("Enter teacher name:");
                    name = scannerIn.nextLine();
                    main.lookUpTeacher(name);
                    break;
                case 9:
                    main.showProfit();
                    break;
                case 10:
                    System.out.println("Exiting the program.");
                    scannerIn.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    @Override
    public void enroll(String studentName, String courseName) {
        Student student = studentsList.get(studentName);
        Course course = coursesList.get(courseName.toLowerCase().trim());
        if (student != null && course != null) {
            student.setCourse(course);
            course.setMoney_earned(course.getMoney_earned() + course.getPrice());
            System.out.println("Student " + student.getName() + " enrolled in course " + course.getName());

        } else {
            System.out.println("Invalid student or course name");
        }
    }


    @Override
    public void assign(String teacherName, String courseName) {
        Teacher teacher = teachersList.get(teacherName);
        Course course = coursesList.get(courseName);
        if (teacher != null && course != null) {
            course.setTeacher(teacher);
            System.out.println("Teacher " + teacher.getName() + " assigned to course " + course.getName());
        } else {
            System.out.println("Invalid teacher or course name");
        }
    }

    @Override
    public void showCourses() {
        System.out.println("These are the Courses:");
        for (Course course : coursesList.values()) {
            System.out.println(course);
        }
    }

    @Override
    public void lookUpCourse(String courseName) {
        String courseN = courseName.trim().toLowerCase();
        Course course = coursesList.get(courseN);
        if (course != null) {
            System.out.println("This is the course: "+course);
        } else {
            System.out.println("Course not found");
        }
    }

    @Override
    public void showStudents() {
        System.out.println("The students are: ");
        for (Student student : studentsList.values()) {
            System.out.println(student);
        }
    }

    @Override
    public void lookUpStudent(String studentName) {
        Student student = studentsList.get(studentName);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    @Override
    public void showTeachers() {
        System.out.println("Teachers:");
        for (Teacher teacher : teachersList.values()) {
            System.out.println(teacher);
        }
    }

    @Override
    public void lookUpTeacher(String teacherName) {
        Teacher teacher = teachersList.get(teacherName);
        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Teacher not found");
        }
    }

    @Override
    public void showProfit() {
        double totalEarnings = 0;
        for (Course course : coursesList.values()) {
            totalEarnings += course.getMoney_earned();
        }

        double totalSalaries = 0;
        for (Teacher teacher : teachersList.values()) {
            totalSalaries += teacher.getSalary();
        }

        double profit = totalEarnings - totalSalaries;
        System.out.println("Total profit: " + profit);
    }
}
