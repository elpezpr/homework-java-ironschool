package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {

    static Course course1;
    static Teacher teacher1;
    static Student student1;

    @BeforeAll
    static void beforeAll() {
        course1 = new Course("Math", 300);
        teacher1 = new Teacher("Gabriel", 1000);
        student1 = new Student("luie", "3476 Wonderland St.", "luie@example.com");
    }

    @Test
    void main() {


        System.out.println(course1.getCourseId().getClass());

        System.out.println(teacher1);
        student1.setCourse(course1);
        System.out.println("This is the student with course: "+student1);
        System.out.println(student1.getCourse());

    }
}