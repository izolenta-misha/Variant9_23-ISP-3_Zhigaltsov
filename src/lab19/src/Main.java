import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА: РАБОТА С ФАЙЛАМИ ===\n");

        // 1. Создание услуг
        Service internet = new Service(ServiceType.INTERNET, "Базовый тариф", 60, 500.0);
        Service cleaning = new Service(ServiceType.CLEANING, "Премиум-клининг", 180, 2500.0);
        Service delivery = new Service(ServiceType.DELIVERY, "Экспресс доставка", 45, 800.0);
        Service internetPro = new Service(ServiceType.INTERNET, "Про тариф", 120, 900.0);
        Service standardDelivery = new Service(ServiceType.DELIVERY, "Стандарт доставка", 60, 500.0);

        // 2. Создание коллекции услуг
        List<Service> services = new ArrayList<>();
        services.add(internet);
        services.add(cleaning);
        services.add(delivery);
        services.add(internetPro);
        services.add(standardDelivery);

        System.out.println("Создано услуг: " + services.size());

        // 3. Создание контейнеров
        ServiceContainer<Service> singleContainer = new ServiceContainer<>("Одиночный контейнер", internet);
        ServiceContainer<Service> listContainer = new ServiceContainer<>("Групповой контейнер",
                Arrays.asList(cleaning, delivery, internetPro));

        List<ServiceContainer<Service>> containers = new ArrayList<>();
        containers.add(singleContainer);
        containers.add(listContainer);

        System.out.println("Создано контейнеров: " + containers.size());

        // 4. Определение путей для файлов
        String baseDir = "lab_data";
        String servicesFile = baseDir + File.separator + "services.txt";
        String containersFile = baseDir + File.separator + "containers_info.txt";

        System.out.println("\n=== СОЗДАНИЕ ДИРЕКТОРИИ ===");

        // 5. Создание директории
        boolean dirCreated = FileManager.createDataDirectory(baseDir);

        if (dirCreated) {
            System.out.println("\n=== ЗАПИСЬ ДАННЫХ В ФАЙЛЫ ===");

            // 6. Запись списка услуг в файл
            System.out.println("\n1. Запись списка услуг в файл...");
            boolean servicesWritten = FileManager.writeServicesToFile(services, servicesFile);

            if (servicesWritten) {
                FileManager.checkFileInfo(servicesFile);
            }

            // 7. Запись информации о контейнерах
            System.out.println("\n2. Запись информации о контейнерах...");
            boolean containersWritten = FileManager.writeContainersInfoToFile(containers, containersFile);

            if (containersWritten) {
                FileManager.checkFileInfo(containersFile);
            }

            // 8. Запись отдельного контейнера в файл
            System.out.println("\n3. Запись отдельного контейнера...");
            String singleContainerFile = baseDir + File.separator + "single_container.txt";
            FileManager.writeServiceContainerToFile(singleContainer, singleContainerFile);
            FileManager.checkFileInfo(singleContainerFile);

            // 9. Запись статистики по типам услуг
            System.out.println("\n4. Запись статистики по типам услуг...");
            FileManager.writeServiceTypesStatistics(services, baseDir);

            // 10. Демонстрация изменения данных и повторной записи
            System.out.println("\n=== ДЕМОНСТРАЦИЯ ИЗМЕНЕНИЯ ДАННЫХ ===");

            // Меняем статус некоторых услуг
            cleaning.setActive(false);
            delivery.setPrice(1000.0);
            internet.setDuration(90);

            // Создаем новую директорию для обновленных данных
            String updatedDir = baseDir + File.separator + "updated";
            FileManager.createDataDirectory(updatedDir);

            // Записываем обновленные данные
            String updatedFile = updatedDir + File.separator + "updated_services.txt";
            System.out.println("\nЗапись обновленных данных...");
            FileManager.writeServicesToFile(services, updatedFile);
            FileManager.checkFileInfo(updatedFile);

            // 11. Создание дополнительных файлов с разными форматами
            System.out.println("\n=== СОЗДАНИЕ ДОПОЛНИТЕЛЬНЫХ ФАЙЛОВ ===");

            // Простой список тарифов
            String tariffsFile = baseDir + File.separator + "tariffs_list.txt";
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(tariffsFile)))) {
                writer.println("СПИСОК ТАРИФОВ");
                writer.println("=".repeat(30));
                for (Service service : services) {
                    writer.println(service.getTariff() + " | " +
                            service.getType().getDescription() + " | " +
                            service.getPrice() + " руб.");
                }
                System.out.println("Список тарифов создан: " + tariffsFile);
            } catch (IOException e) {
                System.out.println("Ошибка при создании списка тарифов: " + e.getMessage());
            }

            // Файл с активными услугами
            String activeFile = baseDir + File.separator + "active_services.txt";
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(activeFile)))) {
                writer.println("АКТИВНЫЕ УСЛУГИ");
                writer.println("=".repeat(30));
                int activeCount = 0;
                for (Service service : services) {
                    if (service.isActive()) {
                        writer.println("- " + service.getTariff());
                        activeCount++;
                    }
                }
                writer.println("\nВсего активных услуг: " + activeCount);
                System.out.println("Список активных услуг создан: " + activeFile);
            } catch (IOException e) {
                System.out.println("Ошибка при создании списка активных услуг: " + e.getMessage());
            }

            // 12. Итоговая информация
            System.out.println("\n=== ИТОГИ РАБОТЫ ===");
            System.out.println("Все файлы успешно созданы в директории: " + new File(baseDir).getAbsolutePath());
            System.out.println("\nСозданные файлы:");

            File dataDir = new File(baseDir);
            if (dataDir.exists() && dataDir.isDirectory()) {
                File[] files = dataDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            System.out.println("  • " + file.getName() + " (" + file.length() + " байт)");
                        } else if (file.isDirectory()) {
                            System.out.println("  • [Папка] " + file.getName());
                        }
                    }
                }
            }

            System.out.println("\nДля проверки откройте папку: " + dataDir.getAbsolutePath());
            System.out.println("\n=== РАБОТА ПРОГРАММЫ ЗАВЕРШЕНА ===");

        } else {
            System.out.println("Ошибка: не удалось создать директорию. Работа программы прервана.");
        }
    }
}