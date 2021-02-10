package org.spbstu.shamarorostislav;

import java.util.Map;

public class Subject {
    private final String name;

    private final Map<Student, Integer> grades;

    public Subject(String subject, Map<Student, Integer> grades) {
        this.name = subject;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public Map<Student, Integer> getGrades() {
        return grades;
    }
}

