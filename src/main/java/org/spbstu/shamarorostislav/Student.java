package org.spbstu.shamarorostislav;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student {
    public static final int MIN_MARK = 1;
    public static final int MAX_MARK = 5;
    private final String fullName ;
    private final Map<String, Integer> grades;

    public Student(@NotNull String fullName, Map<@NotNull String, Integer> grades) {
        if (fullName.isEmpty())
            throw new IllegalArgumentException("Name must be not empty");
        if (grades != null) {
            for (Map.Entry<String, Integer> grade : grades.entrySet()) {
                if (grade.getKey().isEmpty())
                    throw new IllegalArgumentException("Subject's name must be not empty");
                if (grade.getValue() != null && (grade.getValue() < MIN_MARK || grade.getValue() > MAX_MARK))
                    throw new IllegalArgumentException(
                            "Mark must be in [" + MIN_MARK + ", " + MAX_MARK + ", but was: " + grade.getValue()
                    );
            }
            for (String subject: grades.keySet())
                if (grades.keySet().stream().map(String::toLowerCase).filter(str -> str.equalsIgnoreCase(subject)).count() != 1)
                    throw new IllegalArgumentException("Subject cannot be repeated");
            this.grades = grades;
        } else {
            this.grades = new HashMap<>();
        }
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
    public Map<String, Integer> getGrades() {
        return this.grades;
    }

        @Override
    public boolean equals(Object other) {
        if (!(other instanceof Student)) return false;
        if (((Student) other).getFullName() == null || ((Student) other).getFullName().isEmpty()) return false;
        if (((Student) other).getGrades() == null) return false;
        for (Map.Entry<String, Integer> grade : ((Student) other).getGrades().entrySet()) {
                if (grade.getKey().isEmpty()) return false;
                if (grade.getValue() != null && (grade.getValue() < MIN_MARK || grade.getValue() > MAX_MARK))
                   return false;
            }
        if (((Student) other).getFullName().equals(this.getFullName())){
            if (this.getGrades().size() != (((Student) other).getGrades().size())) return false;
            for (String key: this.getGrades().keySet())
                if (!this.getGrades().get(key).equals((((Student) other).getGrades().get(key)))) return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, grades);
    }
}
