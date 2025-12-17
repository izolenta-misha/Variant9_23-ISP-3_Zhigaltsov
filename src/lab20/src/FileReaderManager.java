import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderManager {

    // Читает файл построчно и возвращает список строк
    public static List<String> readFileLines(String filePath) {
        List<String> lines = new ArrayList<>();

        // Проверяем существование файла
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Ошибка: файл не существует - " + filePath);
            return lines;
        }

        if (!file.isFile()) {
            System.out.println("Ошибка: указанный путь не является файлом - " + filePath);
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Начало чтения файла: " + file.getName());
            System.out.println("Размер файла: " + file.length() + " байт");
            System.out.println("=".repeat(50));

            String line;
            int lineNumber = 1;

            // Построчное чтение файла
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                System.out.println("Строка #" + lineNumber + ": " + line);
                lineNumber++;
            }

            System.out.println("=".repeat(50));
            System.out.println("Чтение файла завершено");
            System.out.println("Прочитано строк: " + lines.size());

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return lines;
    }

    // Читает файл и возвращает полное содержимое как одну строку
    public static String readFileAsString(String filePath) {
        StringBuilder content = new StringBuilder();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return "Файл не найден: " + filePath;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
                lineCount++;
            }

            System.out.println("Файл прочитан полностью: " + file.getName());
            System.out.println("Количество строк: " + lineCount);

        } catch (IOException e) {
            return "Ошибка чтения: " + e.getMessage();
        }

        return content.toString();
    }

    // Читает файл и анализирует структуру данных об услугах
    public static void analyzeServicesFile(String filePath) {
        List<String> lines = readFileLines(filePath);

        if (lines.isEmpty()) {
            return;
        }

        System.out.println("\n=== АНАЛИЗ ФАЙЛА С УСЛУГАМИ ===");

        int serviceCount = 0;
        int totalCostLine = -1;
        boolean inServiceBlock = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.contains("Услуга #")) {
                serviceCount++;
                inServiceBlock = true;
            }

            if (line.contains("Общая стоимость всех услуг:")) {
                totalCostLine = i;
            }

            if (inServiceBlock && line.contains("-".repeat(50))) {
                inServiceBlock = false;
            }
        }

        System.out.println("Количество услуг в файле: " + serviceCount);

        if (totalCostLine != -1 && totalCostLine < lines.size()) {
            System.out.println("Итоговая информация: " + lines.get(totalCostLine));
        }

        // Поиск информации о типах услуг
        System.out.println("\nТипы услуг, найденные в файле:");
        for (String line : lines) {
            if (line.contains("Услуга:")) {
                // Извлекаем тип услуги из строки
                String[] parts = line.split("\\)");
                if (parts.length > 0) {
                    System.out.println("  • " + parts[0].replace("Услуга:", "").trim());
                }
            }
        }
    }

    // Читает и анализирует файл с контейнерами
    public static void analyzeContainersFile(String filePath) {
        List<String> lines = readFileLines(filePath);

        if (lines.isEmpty()) {
            return;
        }

        System.out.println("\n=== АНАЛИЗ ФАЙЛА С КОНТЕЙНЕРАМИ ===");

        int containerCount = 0;
        double totalCost = 0;

        for (String line : lines) {
            if (line.contains("Контейнер #")) {
                containerCount++;
            }

            if (line.contains("Общая стоимость всех услуг:")) {
                // Извлекаем числовое значение стоимости
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    try {
                        String costStr = parts[1].replace("руб.", "").trim();
                        totalCost = Double.parseDouble(costStr);
                    } catch (NumberFormatException e) {
                        // Игнорируем ошибки парсинга
                    }
                }
            }
        }

        System.out.println("Количество контейнеров: " + containerCount);
        System.out.println("Общая стоимость всех услуг: " + totalCost + " руб.");
    }

    // Читает статистический файл и выводит сводку
    public static void analyzeStatisticsFile(String filePath) {
        System.out.println("\n=== ЧТЕНИЕ СТАТИСТИЧЕСКОГО ФАЙЛА ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean inStatisticsSection = false;

            System.out.println("Содержимое файла " + new File(filePath).getName() + ":");
            System.out.println("-".repeat(50));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                if (line.contains("СТАТИСТИКА ПО ТИПАМ УСЛУГ")) {
                    inStatisticsSection = true;
                }

                if (inStatisticsSection && line.contains("Тип:")) {
                    // Выводим только важную статистическую информацию
                    System.out.println("[Статистика] " + line);
                }
            }

            System.out.println("-".repeat(50));
            System.out.println("Файл статистики прочитан успешно");

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла статистики: " + e.getMessage());
        }
    }

    // Читает файл и подсчитывает различные метрики
    public static void calculateFileMetrics(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл не найден для анализа: " + filePath);
            return;
        }

        System.out.println("\n=== МЕТРИКИ ФАЙЛА: " + file.getName() + " ===");

        int totalLines = 0;
        int emptyLines = 0;
        int wordCount = 0;
        int charCount = 0;
        List<String> longestLines = new ArrayList<>();
        int maxLineLength = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                totalLines++;
                charCount += line.length();

                if (line.trim().isEmpty()) {
                    emptyLines++;
                }

                // Подсчет слов (грубое приближение)
                wordCount += line.split("\\s+").length;

                // Поиск самых длинных строк
                if (line.length() > maxLineLength) {
                    maxLineLength = line.length();
                    longestLines.clear();
                    longestLines.add("Строка #" + totalLines + ": " + line);
                } else if (line.length() == maxLineLength && maxLineLength > 0) {
                    longestLines.add("Строка #" + totalLines + ": " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при анализе файла: " + e.getMessage());
            return;
        }

        System.out.println("Общее количество строк: " + totalLines);
        System.out.println("Пустые строки: " + emptyLines);
        System.out.println("Количество слов: " + wordCount);
        System.out.println("Общее количество символов: " + charCount);
        System.out.println("Размер файла: " + file.length() + " байт");

        if (totalLines > 0) {
            System.out.println("Средняя длина строки: " + (charCount / totalLines) + " символов");
            System.out.println("Процент пустых строк: " +
                    String.format("%.1f", (emptyLines * 100.0 / totalLines)) + "%");
        }

        if (!longestLines.isEmpty()) {
            System.out.println("\nСамые длинные строки (" + maxLineLength + " символов):");
            for (String longLine : longestLines) {
                if (longLine.length() > 100) {
                    System.out.println("  " + longLine.substring(0, 100) + "...");
                } else {
                    System.out.println("  " + longLine);
                }
            }
        }
    }

    // Ищет конкретную информацию в файле
    public static void searchInFile(String filePath, String searchTerm) {
        System.out.println("\n=== ПОИСК В ФАЙЛЕ: '" + searchTerm + "' ===");

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл не найден: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 1;
            int matchCount = 0;

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(searchTerm.toLowerCase())) {
                    System.out.println("Найдено в строке #" + lineNumber + ": " + line);
                    matchCount++;
                }
                lineNumber++;
            }

            System.out.println("\nВсего найдено совпадений: " + matchCount);

        } catch (IOException e) {
            System.out.println("Ошибка при поиске в файле: " + e.getMessage());
        }
    }

    // Сравнивает два файла построчно
    public static void compareFiles(String filePath1, String filePath2) {
        System.out.println("\n=== СРАВНЕНИЕ ФАЙЛОВ ===");

        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        if (!file1.exists() || !file2.exists()) {
            System.out.println("Один или оба файла не существуют");
            return;
        }

        List<String> lines1 = readFileLines(filePath1);
        List<String> lines2 = readFileLines(filePath2);

        System.out.println("\nРезультаты сравнения:");
        System.out.println("Файл 1: " + file1.getName() + " (" + lines1.size() + " строк)");
        System.out.println("Файл 2: " + file2.getName() + " (" + lines2.size() + " строк)");

        if (lines1.size() != lines2.size()) {
            System.out.println("Файлы имеют разное количество строк");
        } else {
            System.out.println("Файлы имеют одинаковое количество строк");
        }

        // Проверяем, содержат ли файлы одинаковые строки
        boolean areIdentical = true;
        int minLines = Math.min(lines1.size(), lines2.size());

        for (int i = 0; i < minLines; i++) {
            if (!lines1.get(i).equals(lines2.get(i))) {
                System.out.println("Различие в строке " + (i + 1) + ":");
                System.out.println("  Файл 1: " + lines1.get(i));
                System.out.println("  Файл 2: " + lines2.get(i));
                areIdentical = false;
                break;
            }
        }

        if (areIdentical && lines1.size() == lines2.size()) {
            System.out.println("Файлы идентичны");
        }
    }
}