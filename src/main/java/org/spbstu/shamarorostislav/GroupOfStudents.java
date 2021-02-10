package org.spbstu.shamarorostislav;

import java.util.Collection;
import java.util.Map;

/*
Хранит собственный номер, список студентов и их успеваемость по различным предметам.

Операции: конструктор, добавить/удалить студента (по ФИО),
добавить/удалить предмет, добавить/удалить/изменить оценку заданного студента
по заданному предмету.
*/

public class GroupOfStudents {

    public GroupOfStudents(Collection<Student> students, Collection<Subject> subjects) {
        this.students = students;
        this.subjects = subjects;
    }

    private final Collection<Subject> subjects;
    private final Collection<Student> students;

    public boolean addStudent(Student student) {
        if (student == null) return false;
        students.add(student);
        return true;
    }

    public boolean deleteStudent(String name) {
        for (Student item : students)
            if (name.equals(item.getName())) {
                students.remove(item);
                return true;
            }
        return false;
    }

    public boolean addSubject(Subject subject) {
        if (subject == null) return false;
        subjects.add(subject);
        return true;
    }

    public boolean deleteSubject(String subject) {
        for (Subject item : subjects)
            if (subject.equals(item.getName())) {
                subjects.remove(item);
                return true;
            }
        return false;
    }

    public Map<Student, Integer> addGrade(String name, String subject, int grade) {
        Map<Student,Integer> NecessarySubject = null;
        for (Subject item : subjects)
            if (subject.equals(item.getName())) NecessarySubject = item.getGrades();

            assert NecessarySubject != null;

            for (Map.Entry<Student, Integer> entry: NecessarySubject.entrySet())
        if (entry.getKey().getName().equals(name))
            NecessarySubject.put(entry.getKey(),grade);
            return NecessarySubject;
    }

    public Map<Student, Integer> deleteGrade(String name, String subject) {
        Map<Student,Integer> NecessarySubject = null;
        for (Subject item : subjects)
            if (subject.equals(item.getName())) NecessarySubject = item.getGrades();

        assert NecessarySubject != null;

        for (Map.Entry<Student, Integer> entry: NecessarySubject.entrySet())
            if (entry.getKey().getName().equals(name))
                NecessarySubject.put(entry.getKey(),null);
        return NecessarySubject;
    }




}

