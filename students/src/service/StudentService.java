package service;

import model.Student;
import util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public void add(String name, int age, double grade) {
        Validator.validateName(name);
        Validator.validateAge(age);
        Validator.validateGrade(grade);
        students.add(new Student(name, age, grade));
    }

    public void addAll(List<Student> other) {
        students.addAll(other);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public List<String> filterByMinAgeAndFormat(int minAge) {
        return students.stream()
                .filter(s -> s.getAge() >= minAge)
                .sorted(Comparator.comparingInt(Student::getAge))
                .map(s -> s.getName() + " | возраст: " + s.getAge() + " | балл: " + s.getGrade())
                .collect(Collectors.toList());
    }
}