package org.spbstu.shamarorostislav;

import java.security.Key;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.*;
import java.util.List;
import java.util.Objects;

import static org.spbstu.shamarorostislav.Student.MAX_MARK;
import static org.spbstu.shamarorostislav.Student.MIN_MARK;


/*
Хранит собственный номер, список студентов и их успеваемость по различным предметам.

Операции: конструктор, добавить/удалить студента (по ФИО),
добавить/удалить предмет, добавить/удалить/изменить оценку заданного студента
по заданному предмету.
*/


public class GroupOfStudents {

    private String groupNumber;

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    private final List<Student> students = new ArrayList<>();

        public List<Student> getGroup() {
        return this.students;
    }


    private boolean checkIsValid(Object obj) {

        if (obj instanceof Integer) return (Integer) obj >= MIN_MARK && (Integer) obj <= MAX_MARK;
        if (obj == null) return false;
        if (obj instanceof String) return !((String) obj).isEmpty();
        return true;
    }

    public boolean addStudent(Student student) {
        if (!checkIsValid(student)) return false;
        students.add(student);
        System.out.println("Студент появился в списке!");
        return true;
    }

    public GroupOfStudents(Student... students) {
        if (checkIsValid(students))
            for (Student st : students) {
                this.addStudent(st);
            }
    }

    private Student getNecessaryStudent(String name) {
        for (Student st : students)
            if (name.toLowerCase().equals(st.getFullName().toLowerCase()))
                return st;
            return null;
    }

    public boolean deleteStudent(String name) {
        if (!checkIsValid(name)) return false;
        if (!checkIsValid(getNecessaryStudent(name))) return false;
        students.remove(getNecessaryStudent(name));
        System.out.println("Теперь студент " + name + " отсутствует в списке!");
        return true;
    }

    public boolean addSubject(String subject) {
        if (!checkIsValid(subject)) return false;

        for (Student st : students) {
            if (!st.getGrades().keySet().stream().map(String::toLowerCase).collect(Collectors.toList()).contains(subject.toLowerCase()))
                st.getGrades().put(subject, null);
        }
        System.out.println("Предмет " + subject + " появился у каждого студента!");
        return true;
    }

    public boolean deleteSubject(String subject) {
        if (!checkIsValid(subject)) return false;
        for (Student st : students)
            st.getGrades().remove(subject);
        System.out.println("Предмет " + subject + " удален у каждого студента!");
        return true;
    }

    public boolean addGrade(String student, String subject, Integer grade) {
        if (!checkIsValid(student)) return false;
        if (!checkIsValid(subject)) return false;
        if (!checkIsValid(grade)) return false;
        if (!checkIsValid(getNecessaryStudent(student))) return false;

        if (Objects.requireNonNull(getNecessaryStudent(student)).getGrades().containsKey(subject) &&
                Objects.requireNonNull(getNecessaryStudent(student)).getGrades().get(subject) == null)
            Objects.requireNonNull(getNecessaryStudent(student)).getGrades().put(subject, grade);
                else return false;
        System.out.println("Оценка добавлена у " + student + " по предмету " + subject + "!");
                return true;
    }

    public boolean changeGrade(String student, String subject, Integer grade) {
        if (!checkIsValid(student)) return false;
        if (!checkIsValid(subject)) return false;
        if (!checkIsValid(grade)) return false;
        if (!checkIsValid(getNecessaryStudent(student))) return false;

        if (Objects.requireNonNull(getNecessaryStudent(student)).getGrades().containsKey(subject) &&
                Objects.requireNonNull(getNecessaryStudent(student)).getGrades().get(subject) != null)
            Objects.requireNonNull(getNecessaryStudent(student)).getGrades().put(subject, grade);
        else return false;
        System.out.println("Оценка по предмету " + subject + " изменена у студента " +  student + "!");
        return true;
    }

    public boolean deleteGrade(String student, String subject) {
        if (!checkIsValid(student)) return false;
        if (!checkIsValid(subject)) return false;
        if (!checkIsValid(getNecessaryStudent(student))) return false;

        if (Objects.requireNonNull(getNecessaryStudent(student)).getGrades().containsKey(subject) &&
                Objects.requireNonNull(getNecessaryStudent(student)).getGrades().get(subject) != null)
        Objects.requireNonNull(getNecessaryStudent(student)).getGrades().put(subject, null);
        else return false;
        System.out.println("Оценка по предмету " + subject + " удалена у студента " +  student + "!");
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
    }



