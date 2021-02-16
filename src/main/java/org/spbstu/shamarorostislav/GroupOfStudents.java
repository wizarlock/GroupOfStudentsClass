package org.spbstu.shamarorostislav;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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


    public void addStudent(Student student) {
        if (student == null || student.getFullName().length() == 0)
            throw new IllegalArgumentException("IncorrectStudent");
        students.add(student);
    }

    public GroupOfStudents(Student... students) {
        if (students != null)
            for (Student st : students) {
                this.addStudent(st);
            }
    }

    public void deleteStudent(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("IncorrectStudent");
        students.removeIf(item -> name.toLowerCase().equals(item.getFullName().toLowerCase()));
    }

    public void addSubject(String subject) {
        if (subject == null || subject.length() == 0) throw new IllegalArgumentException("IncorrectSubject");
        for (Student st : students) {
            if (!st.getGrades().containsKey(subject)) st.getGrades().put(subject, null);
        }
    }

    public void deleteSubject(String subject) {
        if (subject == null || subject.length() == 0) throw new IllegalArgumentException("IncorrectSubject");
        for (Student st : students)
            st.getGrades().remove(subject);
    }

    public void addGrade(String student, String subject, int grade) {
        if (subject == null || subject.length() == 0) throw new IllegalArgumentException("IncorrectSubject");
        if (student == null || student.length() == 0) throw new IllegalArgumentException("IncorrectStudent");

        boolean flag = false;

        for (Student st : students)
            if (student.toLowerCase().equals(st.getFullName().toLowerCase())) {
                flag = true;
                break;
            }

        if (!flag) throw new IllegalArgumentException("Student is absent");

        for (Student st : students)
            if (student.toLowerCase().equals(st.getFullName().toLowerCase()))
                if (st.getGrades().containsKey(subject) && st.getGrades().get(subject) == null)
                    st.getGrades().put(subject, grade);
                else throw new IllegalArgumentException("The subject is missing or grade is present");
    }

    public void changeGrade(String student, String subject, int grade) {
        if (subject == null || subject.length() == 0) throw new IllegalArgumentException("IncorrectSubject");
        if (student == null || student.length() == 0) throw new IllegalArgumentException("IncorrectStudent");

        boolean flag = false;

        for (Student st : students)
            if (student.toLowerCase().equals(st.getFullName().toLowerCase())) {
                flag = true;
                break;
            }

        if (!flag) throw new IllegalArgumentException("Student is absent");

        for (Student st : students)
            if (student.toLowerCase().equals(st.getFullName().toLowerCase()))
                if (st.getGrades().containsKey(subject) && st.getGrades().get(subject) != null)
                    st.getGrades().put(subject, grade);
                else throw new IllegalArgumentException("The subject or grade is missing");
    }

    public void deleteGrade(String student, String subject) {
        if (subject == null || subject.length() == 0) throw new IllegalArgumentException("IncorrectSubject");
        if (student == null || student.length() == 0) throw new IllegalArgumentException("IncorrectStudent");

        boolean flag = false;

        for (Student st : students)
            if (student.toLowerCase().equals(st.getFullName().toLowerCase())) {
                flag = true;
                break;
            }

        if (!flag) throw new IllegalArgumentException("Student is absent");

        for (Student st : students)
            if (student.toLowerCase().equals(st.getFullName().toLowerCase()))
                if (st.getGrades().containsKey(subject) && st.getGrades().get(subject) != null)
                    st.getGrades().put(subject, null);
                else throw new IllegalArgumentException("The subject or grade is missing");
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


