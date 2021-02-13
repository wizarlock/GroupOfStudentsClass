package org.spbstu.shamarorostislav;

import java.util.Map;

public class Student {
    private final String fullName;
    private final Map<String, Integer> grades;

    public Student(String fullName, Map<String, Integer> grades) {
        this.fullName = fullName;
        this.grades = grades;
    }

    public String getFullName() {
        return fullName;
    }
    public Map<String, Integer> getGrades() {
        return grades;
    }

}
