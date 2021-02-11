package org.spbstu.shamarorostislav;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


class GroupOfStudentsTest {

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Subject> subjects = new ArrayList<>();

    @Before
            void init () {
        students.add(new Student ("Шамаро Ростислав Витальевич"));
        students.add(new Student("Буглаев Семен Алексеевич"));
        students.add(new Student("Силюков Максим Андреевич "));
        students.add(new Student("Мостовая Ирина Александровна"));
        students.add(new Student("Морозов Никита Ильич"));

        Map<Student, Integer> map = new HashMap<>();
        map.put(students.get(0), 5);
        map.put(students.get(1), 5);
        map.put(students.get(2), 3);
        map.put(students.get(3), 3);
        map.put(students.get(4), 4);

        subjects.add(new Subject("Высшая Математика", map));

        map.clear();

        map.put(students.get(0), 3);
        map.put(students.get(1), 4);
        map.put(students.get(2), 2);
        map.put(students.get(3), 5);
        map.put(students.get(4), 5);

        subjects.add(new Subject("История", map));

        map.clear();

        map.put(students.get(0), 3);
        map.put(students.get(1), 2);
        map.put(students.get(2), 3);
        map.put(students.get(3), 2);
        map.put(students.get(4), 3);

        subjects.add(new Subject("Физика", map));
    }

    @Test
    void addStudent() {
        Student s1 = new Student("Мелехина Елена Сергеевна");
        Student s2 = new Student("Макейкин Александр Сергеевич");
        
        assertTrue(students.addStudent(s1));
        assertTrue(students.addStudent(s2));
        assertFalse(students.addStudent(s1));






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