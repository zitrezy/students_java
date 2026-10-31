package model;

public class Student {
    private final String name;
    private final int age;
    private final double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName()  { return name; }
    public int    getAge()   { return age; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return name + " | возраст: " + age + " | балл: " + grade;
    }
}