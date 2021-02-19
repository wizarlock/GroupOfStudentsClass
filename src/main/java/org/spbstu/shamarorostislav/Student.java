package org.spbstu.shamarorostislav;

import java.security.Key;
import java.util.Map;
import java.util.Objects;

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

    //Наллпоинтер, чекеры на значения оценок, выдача коллекций отказаться, исключения

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Student)) return false;
        if (((Student) other).getFullName().equals(this.getFullName())){
            if (this.getGrades().size() != (((Student) other).getGrades().size())) return false;
            for (String key: this.getGrades().keySet())
                if (!this.getGrades().get(key).equals((((Student) other).getGrades().get(key)))) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, grades);
    }
}
