package util;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileLoader {
    private FileLoader() {}

    public static List<Student> loadStudents(String path) throws IOException {
        List<Student> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Student student = parseLine(line);
                if (student != null) {
                    result.add(student);
                }
            }
        }
        return result;
    }

    private static Student parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("Пропущена строка (неверный формат): " + line);
            return null;
        }

        try {
            String name  = parts[0].trim();
            int    age   = Integer.parseInt(parts[1].trim());
            double grade = Double.parseDouble(parts[2].trim().replace(',', '.'));

            Validator.validateName(name);
            Validator.validateAge(age);
            Validator.validateGrade(grade);

            return new Student(name, age, grade);
        } catch (IllegalArgumentException e) {
            System.out.println("Пропущена строка (" + e.getMessage() + "): " + line);
            return null;
        }
    }
}