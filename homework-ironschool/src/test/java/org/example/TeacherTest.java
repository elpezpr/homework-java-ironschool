package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    private Teacher teacher1;
    private Teacher teacher2;

    @BeforeEach
    public void setUp() {
        Teacher.resetNextId(); // Method to reset nextId (added for testing purposes)
        teacher1 = new Teacher("Wilmer Brock", 40000);
        teacher2 = new Teacher("Eddie Anderson", 80000);
    }

    @Test
    void setName_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacher1.setName(null);
        });
    }

    @Test
    void testSetName_Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacher1.setName("");
        });
    }

    @Test
    void testSetSalary_Negative() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacher1.setSalary(-1000);
        });
    }

    @Test
    public void testGenerateTeacherId() {
        // Check the IDs of the teachers
        assertEquals("T001", teacher1.getTeacherId());
        assertEquals("T002", teacher2.getTeacherId());
    }
}