import model.Student;
import service.StudentService;
import util.FileLoader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service = new StudentService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    ===== МЕНЮ =====
                     1. Добавить данные вручную
                     2. Загрузить данные из файла
                     3. Обработать данные
                     0. Выход
                    """);

            System.out.print("Выберите пункт меню: ");
            String input = scanner.nextLine();

            int command;
            try {
                command = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: необходимо ввести число.");
                continue;
            }

            switch (command) {
                case 1 -> { System.out.println("Добавление данных вручную"); addManual(); }
                case 2 -> { System.out.println("Загрузка данных из файла");   loadFromFile(); }
                case 3 -> { System.out.println("Обработка данных");           processData(); }
                case 0 -> { System.out.println("Завершение работы программы…"); return; }
                default -> System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void addManual() {
        try {
            System.out.print("Имя: ");
            String name = scanner.nextLine().trim();

            System.out.print("Возраст: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Средний балл (0–5): ");
            double grade = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));

            service.add(name, age, grade);
            System.out.println("Студент добавлен.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        System.out.print("Путь к CSV-файлу (формат: имя,возраст,балл): ");
        String path = scanner.nextLine().trim();

        try {
            List<Student> loaded = FileLoader.loadStudents(path);
            service.addAll(loaded);
            System.out.println("Загружено студентов: " + loaded.size());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static void processData() {
        if (service.isEmpty()) {
            System.out.println("Список пуст. Сначала добавьте студентов.");
            return;
        }

        System.out.print("Минимальный возраст для фильтра: ");
        int minAge;
        try {
            minAge = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число.");
            return;
        }

        System.out.println("\n Результат (возраст >= " + minAge + ", сортировка по возрасту)");
        service.filterByMinAgeAndFormat(minAge).forEach(System.out::println);
    }
}
