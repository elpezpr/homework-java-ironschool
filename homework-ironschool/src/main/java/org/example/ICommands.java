package org.example;

public interface ICommands {

    void enroll(String studentName, String courseName);
    void assign(String teacherName, String courseName);
    void showCourses();
    void lookUpCourse(String courseName);
    void showStudents();
    void lookUpStudent(String studentName);
    void showTeachers();
    void lookUpTeacher(String teacherName);
    void showProfit();

}
