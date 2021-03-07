package org.spbstu.shamarorostislav;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static org.spbstu.shamarorostislav.GroupOfStudents.Student.MAX_MARK;
import static org.spbstu.shamarorostislav.GroupOfStudents.Student.MIN_MARK;

/*
Хранит собственный номер, список студентов и их успеваемость по различным предметам.

Операции: конструктор, добавить/удалить студента (по ФИО),
добавить/удалить предмет, добавить/удалить/изменить оценку заданного студента
по заданному предмету.
*/


public class GroupOfStudents {

    private String groupNumber;

    private static final Logger logger = Logger.getLogger(GroupOfStudents.class);

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    private final List<Student> students = new ArrayList<>();

    public List<Student> getGroup() {
        List<Student> newStudents = new ArrayList<>();
        if (checkIsValid(students)) newStudents.addAll(students);
        return newStudents;
    }

    private boolean checkIsValid(Object obj) {
        if (obj instanceof Integer) return (Integer) obj >= MIN_MARK && (Integer) obj <= MAX_MARK;
        if (obj == null) return false;
        if (obj instanceof String) return !((String) obj).isEmpty();
        if (obj instanceof Optional) return ((Optional<?>) obj).isPresent();
        return true;
    }

    public boolean addStudent(Student student) {
        if (!checkIsValid(student)) throw new IllegalArgumentException("Student is entered incorrectly");
        students.add(student);
        logger.info("Теперь студент " + student + " появился в списке!");
        return true;
    }

    public GroupOfStudents(Student... students) {
        if (checkIsValid(students))
            for (Student st : students) {
                addStudent(st);
            }
    }

    private Optional<Student> getNecessaryStudent(String name) {
        return students.stream().filter(str -> str.getFullName().equalsIgnoreCase(name)).findFirst();
    }

    public boolean deleteStudent(String name) {
        if (!checkIsValid(name)) throw new IllegalArgumentException("Name is entered incorrectly");
        if (!checkIsValid(getNecessaryStudent(name))) return false;
        students.remove(getNecessaryStudent(name).orElseThrow());
        logger.info("Теперь студент " + name + " отсутствует в списке!");
        return true;
    }

    public boolean addSubject(String subject) {
        if (!checkIsValid(subject)) throw new IllegalArgumentException("Subject is entered incorrectly");
        for (Student st : students) {
            if (!st.grades.keySet().stream().map(String::toLowerCase).collect(Collectors.toList()).contains(subject.toLowerCase()))
                st.grades.put(subject, null);
        }
        logger.info("Предмет " + subject + " теперь есть у каждого студента!");
        return true;
    }

    public boolean deleteSubject(String subject) {
        if (!checkIsValid(subject)) throw new IllegalArgumentException("Subject is entered incorrectly");
        for (Student st : students) {
            if (st.grades.keySet().stream().noneMatch(sub -> sub.equalsIgnoreCase(subject))) continue;
            st.grades.remove(st.grades.keySet().stream().filter(sub -> sub.equalsIgnoreCase(subject)).findFirst().orElseThrow());
        }
        logger.info("Предмет " + subject + " теперь отсутствует у каждого студента!");
        return true;
    }

    public boolean addGrade(String name, String subject, Integer grade) {
        if (!checkIsValid(name)) throw new IllegalArgumentException("Name is entered incorrectly");
        if (!checkIsValid(subject)) throw new IllegalArgumentException("Subject is entered incorrectly");
        if (!checkIsValid(grade)) throw new IllegalArgumentException("Grade is entered incorrectly");
        if (!checkIsValid(getNecessaryStudent(name))) return false;

        if (getNecessaryStudent(name).orElseThrow().grades.containsKey(subject) &&
                (getNecessaryStudent(name)).orElseThrow().grades.get(subject) == null)
            getNecessaryStudent(name).orElseThrow().grades.put(subject, grade);
                else return false;
        logger.info("Оценка добавлена у " + name + " по предмету " + subject + "!");
                return true;
    }

    public boolean changeGrade(String name, String subject, Integer grade) {
        if (!checkIsValid(name)) throw new IllegalArgumentException("Name is entered incorrectly");
        if (!checkIsValid(subject)) throw new IllegalArgumentException("Subject is entered incorrectly");
        if (!checkIsValid(grade)) throw new IllegalArgumentException("Grade is entered incorrectly");
        if (!checkIsValid(getNecessaryStudent(name))) return false;

        if (getNecessaryStudent(name).orElseThrow().grades.containsKey(subject) &&
               getNecessaryStudent(name).orElseThrow().grades.get(subject) != null)
            getNecessaryStudent(name).orElseThrow().grades.put(subject, grade);
        else return false;
        logger.info("Оценка по предмету " + subject + " изменена у студента " + name + "!");
        return true;
    }

    public boolean deleteGrade(String name, String subject) {
        if (!checkIsValid(name)) throw new IllegalArgumentException("Name is entered incorrectly");
        if (!checkIsValid(subject)) throw new IllegalArgumentException("Subject is entered incorrectly");
        if (!checkIsValid(getNecessaryStudent(name))) return false;

        if (getNecessaryStudent(name).orElseThrow().grades.containsKey(subject) &&
                (getNecessaryStudent(name)).orElseThrow().grades.get(subject) != null) getNecessaryStudent(name).orElseThrow()
                .grades.put(subject, null);
        else return false;
        logger.info("Оценка по предмету " + subject + " удалена у студента " + name + "!");
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, students);
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof GroupOfStudents)) return false;
        if (this.getGroup().size() != ((GroupOfStudents) other).getGroup().size()) return false;

        for (int i = 0; i < this.getGroup().size() - 1; i++) {
            if (!((GroupOfStudents) other).getGroup().contains(this.getGroup().get(i))) return false;
        }
        return true;
    }

    public static class Student {
        public static final int MIN_MARK = 1;
        public static final int MAX_MARK = 5;
        private final String fullName ;
        private final Map<String, Integer> grades;

        public Student(@NotNull String fullName, Map<@NotNull String, Integer> grades) {
            Map <String, Integer> newGrades = new HashMap<>();
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
                for (Map.Entry<String, Integer> gr : grades.entrySet()) newGrades.put(gr.getKey(), gr.getValue());
                this.grades = newGrades;
            } else {
                this.grades = new HashMap<>();
            }
            this.fullName = fullName;
        }

        public String getFullName() {
            return fullName;
        }

        public Map<String, Integer> getGrades() {
            Map <String, Integer> newGrades = new HashMap<>();
            for (Map.Entry<String, Integer> gr : grades.entrySet()) newGrades.put(gr.getKey(), gr.getValue());
            return newGrades;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Student)) return false;
            if (((Student) other).getFullName().equals(this.getFullName())){
                if (this.grades.size() != (((Student) other).grades.size())) return false;
                for (String key: this.grades.keySet())
                    if (!this.grades.get(key).equals((((Student) other).grades.get(key)))) return false;
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
}