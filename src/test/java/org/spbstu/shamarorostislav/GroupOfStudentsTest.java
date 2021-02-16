package org.spbstu.shamarorostislav;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class GroupOfStudentsTest {

    GroupOfStudents group;
    GroupOfStudents group1;
    GroupOfStudents group2;

    @BeforeEach
    void init() {

        Map<String, Integer> educationalPerformanceForStudent0 = new HashMap<>();
        educationalPerformanceForStudent0.put("Высшая математика", 5);
        educationalPerformanceForStudent0.put("Физика", 3);
        educationalPerformanceForStudent0.put("Программирование", 5);
        educationalPerformanceForStudent0.put("Физическая культура", 5);
        educationalPerformanceForStudent0.put("Овт", 3);

        Map<String, Integer> educationalPerformanceForStudent1 = new HashMap<>();
        educationalPerformanceForStudent1.put("Высшая математика", 4);
        educationalPerformanceForStudent1.put("Физика", 4);
        educationalPerformanceForStudent1.put("Программирование", 3);
        educationalPerformanceForStudent1.put("Физическая культура", 5);
        educationalPerformanceForStudent1.put("Овт", 5);

        Map<String, Integer> educationalPerformanceForStudent2 = new HashMap<>();
        educationalPerformanceForStudent2.put("Высшая математика", 3);
        educationalPerformanceForStudent2.put("Физика", 5);
        educationalPerformanceForStudent2.put("Программирование", 4);
        educationalPerformanceForStudent2.put("Физическая культура", 5);
        educationalPerformanceForStudent2.put("Овт", 2);

        Map<String, Integer> educationalPerformanceForStudent3 = new HashMap<>();
        educationalPerformanceForStudent3.put("Высшая математика", 4);
        educationalPerformanceForStudent3.put("Физика", 4);
        educationalPerformanceForStudent3.put("Программирование", 3);
        educationalPerformanceForStudent3.put("Физическая культура", 4);
        educationalPerformanceForStudent3.put("Овт", 4);

        Map<String, Integer> educationalPerformanceForStudent4 = new HashMap<>();
        educationalPerformanceForStudent4.put("Высшая математика", 5);
        educationalPerformanceForStudent4.put("Физика", 5);
        educationalPerformanceForStudent4.put("Программирование", 5);
        educationalPerformanceForStudent4.put("Физическая культура", 5);
        educationalPerformanceForStudent4.put("Овт", null);

        group = new GroupOfStudents(
                new Student("Шамаро Ростислав Витальевич", educationalPerformanceForStudent0),
                new Student("Декельман Григорий Павлович", educationalPerformanceForStudent1),
                new Student("Гера Дмитрий Владимирович", educationalPerformanceForStudent2),
                new Student("Пеутина Вероника Игоревна", educationalPerformanceForStudent3),
                new Student("Нигматулин Артем Денисович", educationalPerformanceForStudent4)
        );
    }

    @Test
    void addStudent() {
        Map<String, Integer> educationalPerformanceForStudent5 = new HashMap<>();

        Student s1 = new Student("Поленков Владислав Вадимович", educationalPerformanceForStudent5);
        group.addStudent(s1);
        assertEquals(6, group.getGroup().size());

        Student s2 = new Student("Сухов Артем Сергеевич", educationalPerformanceForStudent5);
        group.addStudent(s2);
        assertEquals(7, group.getGroup().size());

        Student s3 = new Student("", educationalPerformanceForStudent5);
        assertThrows(IllegalArgumentException.class, () -> {
            group.addStudent(s3);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            group.addStudent(null);
        });
    }

    @Test
    void deleteStudent() {

        group.deleteStudent("Декельман Григорий Павлович");
        assertEquals(4, group.getGroup().size());

        group.deleteStudent("Декельман Григорий Павлович");
        assertEquals(4, group.getGroup().size());

        group.deleteStudent("шАмАрО РостисЛав ВитАльЕвич");
        assertEquals(3, group.getGroup().size());

        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteStudent(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteStudent("");
        });
    }

    @Test
    void addSubject() {
        int counter = 0;
        group.addSubject("Русский язык");
        for (Student st : group.getGroup())
            if (st.getGrades().containsKey("Русский язык")) counter++;
        assertEquals(group.getGroup().size(), counter);

        assertThrows(IllegalArgumentException.class, () -> {
            group.addSubject("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addSubject(null);
        });
    }

    @Test
    void deleteSubject() {
        int counter = 0;
        group.deleteSubject("Овт");
        for (Student st : group.getGroup())
            if (!st.getGrades().containsKey("Овт")) counter++;
        assertEquals(group.getGroup().size(), counter);


        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteSubject("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteSubject(null);
        });
    }

    @Test
    void addGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade(null, "Высшая математика", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade("", "Высшая математика", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade("Шамаро Ростислав Витальевич", "", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade("Шамаро Ростислав Витальевич", null, 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade("Сухов Артем Сергеевич", "Овт", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade("Шамаро Ростислав Витальевич", "Алгебра", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.addGrade("Шамаро Ростислав Витальевич", "Овт", 2);
        });

        group.addGrade("Нигматулин Артем Денисович", "Овт", 4);
        Integer gr = 0;
        for (Student st : group.getGroup())
            if (st.getFullName().equals("Нигматулин Артем Денисович"))
                gr = st.getGrades().get("Овт");

        assertEquals(4, gr);
    }

    @Test
    void changeGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade(null, "Высшая математика", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade("", "Высшая математика", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade("Шамаро Ростислав Витальевич", "", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade("Шамаро Ростислав Витальевич", null, 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade("Сухов Артем Сергеевич", "Овт", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade("Шамаро Ростислав Витальевич", "Алгебра", 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.changeGrade("Нигматулин Артем Денисович", "Овт", 2);
        });

        group.changeGrade("Декельман Григорий Павлович", "Овт", 4);
        Integer gr = 0;
        for (Student st : group.getGroup())
            if (st.getFullName().equals("Декельман Григорий Павлович"))
                gr = st.getGrades().get("Овт");

        assertEquals(4, gr);
    }

    @Test
    void deleteGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteGrade(null, "Высшая математика");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteGrade("", "Высшая математика");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteGrade("Шамаро Ростислав Витальевич", "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteGrade("Шамаро Ростислав Витальевич", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteGrade("Сухов Артем Сергеевич", "Овт");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            group.deleteGrade("Шамаро Ростислав Витальевич", "Алгебра");
        });

        group.deleteGrade("Декельман Григорий Павлович", "Овт");
        Integer gr = 0;
        for (Student st : group.getGroup())
            if (st.getFullName().equals("Декельман Григорий Павлович")) {
                gr = st.getGrades().get("Овт");
            }
        assertNull(gr);
    }

    @Test
    void testEquals() {
        Map<String, Integer> educationalPerformanceForStudentShamaro = new HashMap<>();
        educationalPerformanceForStudentShamaro.put("Киберспорт", 4);
        educationalPerformanceForStudentShamaro.put("Бжд", 3);

        Map<String, Integer> educationalPerformanceForStudentSuhov = new HashMap<>();
        educationalPerformanceForStudentSuhov.put("Киберспорт", 5);
        educationalPerformanceForStudentSuhov.put("Бжд", 2);

        group1 = new GroupOfStudents(
                new Student("Шамаро Ростислав Витальевич", educationalPerformanceForStudentShamaro),
                new Student("Сухов Артем Сергеевич", educationalPerformanceForStudentSuhov)
        );
        group2 = new GroupOfStudents(
                new Student("Сухов Артем Сергеевич", educationalPerformanceForStudentSuhov),
                new Student("Шамаро Ростислав Витальевич", educationalPerformanceForStudentShamaro)
        );
        assertEquals(group1, group2);

        group1.deleteStudent("Сухов Артем Сергеевич");
        assertNotEquals(group1, group2);

        group1 = new GroupOfStudents(
                new Student("Шамаро Ростислав Витальевич", educationalPerformanceForStudentSuhov),
                new Student("Сухов Артем Сергеевич", educationalPerformanceForStudentSuhov)
        );
        group2 = new GroupOfStudents(
                new Student("Сухов Артем Сергеевич", educationalPerformanceForStudentSuhov),
                new Student("Шамаро Ростислав Витальевич", educationalPerformanceForStudentShamaro)
        );
        assertNotEquals(group1, group2);
    }
}