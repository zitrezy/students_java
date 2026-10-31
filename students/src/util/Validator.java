package util;

public final class Validator {
    private Validator() {}

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
    }

    public static void validateAge(int age) {
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Возраст должен быть от 1 до 120");
        }
    }

    public static void validateGrade(double grade) {
        if (grade < 0 || grade > 5) {
            throw new IllegalArgumentException("Балл должен быть от 0 до 5");
        }
    }
}