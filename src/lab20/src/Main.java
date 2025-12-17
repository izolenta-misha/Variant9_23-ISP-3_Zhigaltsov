import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА: ЧТЕНИЕ ФАЙЛОВ ===\n");

        // Базовый путь к директории с файлами
        String baseDir = "lab_data";

        // Проверяем существование базовой директории
        File dataDir = new File(baseDir);
        if (!dataDir.exists() || !dataDir.isDirectory()) {
            System.out.println("Ошибка: директория с данными не найдена: " + baseDir);
            System.out.println("Сначала запустите программу из предыдущей лабораторной работы");
            return;
        }

        // Пути к файлам
        String servicesFile = baseDir + File.separator + "services.txt";
        String containersFile = baseDir + File.separator + "containers_info.txt";
        String singleContainerFile = baseDir + File.separator + "single_container.txt";
        String statisticsFile = baseDir + File.separator + "statistics" +
                File.separator + "service_types_stats.txt";
        String updatedFile = baseDir + File.separator + "updated" +
                File.separator + "updated_services.txt";
        String tariffsFile = baseDir + File.separator + "tariffs_list.txt";
        String activeFile = baseDir + File.separator + "active_services.txt";

        System.out.println("Директория с данными: " + dataDir.getAbsolutePath());
        System.out.println("Найденные файлы:");

        // Показываем список файлов в директории
        File[] files = dataDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("  • " + file.getName() + " (" + file.length() + " байт)");
                }
            }
        }
        System.out.println();

        // 1. ДЕМОНСТРАЦИЯ ПОСТРОЧНОГО ЧТЕНИЯ ФАЙЛА
        System.out.println("1. ПОСТРОЧНОЕ ЧТЕНИЕ ФАЙЛА services.txt");
        System.out.println("=".repeat(60));
        FileReaderManager.readFileLines(servicesFile);

        // 2. ЧТЕНИЕ ФАЙЛА КАК СТРОКИ
        System.out.println("\n\n2. ЧТЕНИЕ ФАЙЛА КАК ЦЕЛОЙ СТРОКИ (tariffs_list.txt)");
        System.out.println("=".repeat(60));
        String fileContent = FileReaderManager.readFileAsString(tariffsFile);
        System.out.println("\nПервые 200 символов содержимого:");
        if (fileContent.length() > 200) {
            System.out.println(fileContent.substring(0, 200) + "...");
        } else {
            System.out.println(fileContent);
        }

        // 3. АНАЛИЗ ФАЙЛА С УСЛУГАМИ
        System.out.println("\n\n3. АНАЛИЗ ФАЙЛА С УСЛУГАМИ");
        System.out.println("=".repeat(60));
        FileReaderManager.analyzeServicesFile(servicesFile);

        // 4. АНАЛИЗ ФАЙЛА С КОНТЕЙНЕРАМИ
        System.out.println("\n\n4. АНАЛИЗ ФАЙЛА С КОНТЕЙНЕРАМИ");
        System.out.println("=".repeat(60));
        FileReaderManager.analyzeContainersFile(containersFile);

        // 5. ЧТЕНИЕ СТАТИСТИЧЕСКОГО ФАЙЛА
        System.out.println("\n\n5. ЧТЕНИЕ СТАТИСТИЧЕСКОГО ФАЙЛА");
        System.out.println("=".repeat(60));
        if (new File(statisticsFile).exists()) {
            FileReaderManager.analyzeStatisticsFile(statisticsFile);
        } else {
            System.out.println("Файл статистики не найден: " + statisticsFile);
        }

        // 6. РАСЧЕТ МЕТРИК ФАЙЛА
        System.out.println("\n\n6. РАСЧЕТ МЕТРИК ФАЙЛА containers_info.txt");
        System.out.println("=".repeat(60));
        FileReaderManager.calculateFileMetrics(containersFile);

        // 7. ПОИСК В ФАЙЛАХ
        System.out.println("\n\n7. ПОИСК ИНФОРМАЦИИ В ФАЙЛАХ");
        System.out.println("=".repeat(60));

        System.out.println("\nПоиск 'интернет' в файле services.txt:");
        FileReaderManager.searchInFile(servicesFile, "интернет");

        System.out.println("\nПоиск 'доставка' в файле services.txt:");
        FileReaderManager.searchInFile(servicesFile, "доставка");

        // 8. СРАВНЕНИЕ ФАЙЛОВ
        System.out.println("\n\n8. СРАВНЕНИЕ ФАЙЛОВ");
        System.out.println("=".repeat(60));

        if (new File(updatedFile).exists()) {
            System.out.println("\nСравнение оригинального и обновленного файла:");
            FileReaderManager.compareFiles(servicesFile, updatedFile);
        } else {
            System.out.println("Обновленный файл не найден для сравнения");
        }

        // 9. ЧТЕНИЕ ВСЕХ ФАЙЛОВ В ДИРЕКТОРИИ
        System.out.println("\n\n9. ОБЗОР ВСЕХ ФАЙЛОВ В ДИРЕКТОРИИ");
        System.out.println("=".repeat(60));

        System.out.println("\nЧтение файла active_services.txt:");
        FileReaderManager.readFileLines(activeFile);

        System.out.println("\nЧтение файла single_container.txt:");
        FileReaderManager.readFileLines(singleContainerFile);

        // 10. ДЕМОНСТРАЦИЯ ОШИБОК (попытка чтения несуществующего файла)
        System.out.println("\n\n10. ДЕМОНСТРАЦИЯ ОБРАБОТКИ ОШИБОК");
        System.out.println("=".repeat(60));

        String nonExistentFile = baseDir + File.separator + "non_existent.txt";
        System.out.println("\nПопытка чтения несуществующего файла:");
        FileReaderManager.readFileLines(nonExistentFile);

        // 11. ИТОГОВАЯ ИНФОРМАЦИЯ
        System.out.println("\n\n=== ИТОГИ РАБОТЫ ===");
        System.out.println("=".repeat(60));

        int totalFilesRead = 0;
        long totalBytesRead = 0;

        String[] allFiles = {servicesFile, containersFile, singleContainerFile,
                tariffsFile, activeFile, updatedFile};

        for (String filePath : allFiles) {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                totalFilesRead++;
                totalBytesRead += file.length();
            }
        }

        System.out.println("Всего прочитано файлов: " + totalFilesRead);
        System.out.println("Общий объем данных: " + totalBytesRead + " байт");
        System.out.println("\nВсе операции чтения файлов выполнены успешно!");
        System.out.println("\n=== ЛАБОРАТОРНАЯ РАБОТА ЗАВЕРШЕНА ===");
    }
}