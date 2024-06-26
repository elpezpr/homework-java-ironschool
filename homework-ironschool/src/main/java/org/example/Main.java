package org.example;

import javax.sound.midi.Soundbank;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scannerIn = new Scanner(System.in);
        Teacher teacher = new Teacher();
        Course course = new Course();
        Student student = new Student();
        Map<String, Teacher> teachersList = new HashMap<>();
        Map<String, Course> coursesList = new HashMap<>();
        List<Teacher> teacherList = new ArrayList<>();

        System.out.println("Please enter name to create a School");
        String schoolName = scannerIn.nextLine();
        System.out.println("How many teachers?");
        int numberOfTeachers = scannerIn.nextInt();
        scannerIn.nextLine();

        for (int i = 0; i < numberOfTeachers; i++) {
            System.out.println("Enter teacher"+ (i+1) +" name");
            String teacherName = scannerIn.nextLine();
            teacher.setName(teacherName);
            System.out.println("Enter "+ teacher.getName()+ "'s salary");
            double teacherSalary = scannerIn.nextInt();
            teacher.setSalary(teacherSalary);
            scannerIn.nextLine();

            teachersList.put(teacher.getName(), teacher);
        }
        System.out.println(teachersList);
        System.out.println("Enter the amount of courses");
        int numberOfCourses = scannerIn.nextInt();
        scannerIn.nextLine();

        for (int i = 0; i < numberOfCourses; i++){
            System.out.println("Enter course"+(i+1)+" name" );
            String courseName = scannerIn.nextLine();
            course.setName(courseName);
            System.out.println("Enter course price");
            double coursePrice = scannerIn.nextInt();
            course.setPrice(coursePrice);
            scannerIn.nextLine();
            coursesList.put(course.getName(), course);
        }

        System.out.println(coursesList);

        System.out.println("Enter how many students");
        int numberOfStudents = scannerIn.nextInt();
        scannerIn.nextLine();

        for (int i = 0; i < numberOfStudents; i++){
            System.out.println("Enter student's name");
            String name = scannerIn.nextLine();
            student.setName(name);
            System.out.println("Enter student's address");
            String address = scannerIn.nextLine();
            student.setAddress(address);
            System.out.println("Enter student's email");
            String email = scannerIn.nextLine();
            student.setEmail(email);
        }

    }
}