package org.spbstu.shamarorostislav;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


class GroupOfStudentsTest {

    @Before
            void init () {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(0, "Дурак");
               ArrayList<Subject> subjects = new ArrayList<>();

        Map<Student, Integer> mapa = new HashMap<>();
        mapa.put(students.get(0), 5);
        subjects.add(new Subject("МАТЕША", mapa))
        mapa.put(students.get(1), 5);
    }

    GroupOfStudents st;


    @Test
    void addStudent() {
        assertEquals(1, 1);
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void addSubject() {
    }

    @Test
    void deleteSubject() {
    }

    @Test
    void addGrade() {
    }

    @Test
    void deleteGrade() {
    }
}