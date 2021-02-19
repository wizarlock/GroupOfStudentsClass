package org.spbstu.shamarorostislav;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
        return students;
    }


    public boolean addStudent(Student student) {
        if (student == null || student.getFullName().length() == 0) return false;
        students.add(student);
        System.out.println("Студент появился в списке!");
        return true;
    }

    public GroupOfStudents(Student... students) {
        if (students != null)
            for (Student st : students) {
                this.addStudent(st);
            }
    }

    private Student getNecessaryStudent(String name) {
        for (Student st : students)
            if (name.toLowerCase().equals(st.getFullName().toLowerCase()))
                return st;
        throw new IllegalArgumentException("The student is not on the list");
    }

    public boolean deleteStudent(String name) {
        if (name == null || name.length() == 0) return false;
        students.remove(getNecessaryStudent(name));
        System.out.println("Теперь студента нет в списке!");
        return true;
    }

    public boolean addSubject(String subject) {
        if (subject == null || subject.length() == 0) return false;
        for (Student st : students) {
            if (!st.getGrades().containsKey(subject)) st.getGrades().put(subject, null);
        }
        System.out.println("Предмет появился у каждого студента!");
        return true;
    }

    public boolean deleteSubject(String subject) {
        if (subject == null || subject.length() == 0) return false;
        for (Student st : students)
            st.getGrades().remove(subject);
        System.out.println("Предмет удален у каждого студента!");
        return true;
    }

    public boolean addGrade(String student, String subject, Integer grade) {
        if (subject == null || subject.length() == 0) return false;
        if (student == null || student.length() == 0) return false;
        if (grade < 1 || grade > 5)  return false;
        if (getNecessaryStudent(student).getGrades().containsKey(subject) && getNecessaryStudent(student).getGrades().get(subject) == null)
            getNecessaryStudent(student).getGrades().put(subject, grade);
                else return false;
        System.out.println("Оценка добавлена у студента!");
                return true;
    }

    public boolean changeGrade(String student, String subject, Integer grade) {
        if (subject == null || subject.length() == 0) return false;
        if (student == null || student.length() == 0) return false;
        if (grade < 1 || grade > 5)  return false;
        if (getNecessaryStudent(student).getGrades().containsKey(subject) && getNecessaryStudent(student).getGrades().get(subject) != null)
            getNecessaryStudent(student).getGrades().put(subject, grade);
        else return false;
        System.out.println("Оценка изменена у студента!");
        return true;
    }

    public boolean deleteGrade(String student, String subject) {
        if (subject == null || subject.length() == 0) return false;
        if (student == null || student.length() == 0) return false;
        if (getNecessaryStudent(student).getGrades().containsKey(subject) && getNecessaryStudent(student).getGrades().get(subject) != null)
        getNecessaryStudent(student).getGrades().put(subject, null);
        else return false;
        System.out.println("Оценка удалена у студента!");
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



