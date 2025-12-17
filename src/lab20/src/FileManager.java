import java.io.*;
import java.util.List;

public class FileManager {

    // Создает директорию для хранения данных
    public static boolean createDataDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists()) {
            if (directory.isDirectory()) {
                System.out.println("Директория уже существует: " + directory.getAbsolutePath());
                return true;
            } else {
                System.out.println("Ошибка: путь существует, но это не директория");
                return false;
            }
        }

        boolean created = directory.mkdirs();
        if (created) {
            System.out.println("Директория успешно создана: " + directory.getAbsolutePath());
            return true;
        } else {
            System.out.println("Ошибка при создании директории: " + directory.getAbsolutePath());
            return false;
        }
    }

    // Записывает список услуг в текстовый файл
    public static boolean writeServicesToFile(List<Service> services, String filePath) {
        if (services == null || services.isEmpty()) {
            System.out.println("Список услуг пуст, запись отменена");
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {

            writer.println("=== СПИСОК УСЛУГ ===");
            writer.println("Дата создания: " + new java.util.Date());
            writer.println("Количество услуг: " + services.size());
            writer.println("=".repeat(50));
            writer.println();

            for (int i = 0; i < services.size(); i++) {
                Service service = services.get(i);
                writer.println("Услуга #" + (i + 1));
                writer.println(service.toString());

                if (i < services.size() - 1) {
                    writer.println("-".repeat(50));
                    writer.println();
                }
            }

            writer.println("\n" + "=".repeat(50));
            writer.println("ИТОГИ:");

            double totalCost = 0;
            int totalDuration = 0;
            int activeServices = 0;

            for (Service service : services) {
                totalCost += service.calculateTotalCost();
                totalDuration += service.getDuration();
                if (service.isActive()) {
                    activeServices++;
                }
            }

            writer.println("Общая стоимость всех услуг: " + String.format("%.2f", totalCost) + " руб.");
            writer.println("Общая продолжительность: " + totalDuration + " минут");
            writer.println("Активных услуг: " + activeServices + " из " + services.size());
            writer.println("=".repeat(50));

            System.out.println("Данные успешно записаны в файл: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
            return false;
        }
    }

    // Записывает контейнер услуг в файл
    public static boolean writeServiceContainerToFile(ServiceContainer<Service> container, String filePath) {
        if (container == null) {
            System.out.println("Контейнер пуст, запись отменена");
            return false;
        }

        List<Service> allServices = container.getServiceList();
        if (container.getSingleService() != null) {
            allServices.add(container.getSingleService());
        }

        return writeServicesToFile(allServices, filePath);
    }

    // Записывает информацию о контейнерах в отдельный файл
    public static boolean writeContainersInfoToFile(List<ServiceContainer<Service>> containers, String filePath) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {

            writer.println("=== ИНФОРМАЦИЯ О КОНТЕЙНЕРАХ УСЛУГ ===");
            writer.println("Дата создания: " + new java.util.Date());
            writer.println("Количество контейнеров: " + containers.size());
            writer.println("=".repeat(50));
            writer.println();

            double totalAllCost = 0;
            int totalAllServices = 0;

            for (int i = 0; i < containers.size(); i++) {
                ServiceContainer<Service> container = containers.get(i);
                writer.println("Контейнер #" + (i + 1) + ": " + container.getContainerName());
                writer.println("Тип: " + (container.getSingleService() != null ? "Одиночный" : "Список"));
                writer.println("Количество услуг: " + container.getServiceList().size());

                double containerCost = 0;
                for (Service service : container.getServiceList()) {
                    containerCost += service.calculateTotalCost();
                }
                if (container.getSingleService() != null) {
                    containerCost += container.getSingleService().calculateTotalCost();
                    writer.println("Единичная услуга: " + container.getSingleService().getTariff());
                }

                writer.println("Общая стоимость контейнера: " + String.format("%.2f", containerCost) + " руб.");
                writer.println("-".repeat(50));
                writer.println();

                totalAllCost += containerCost;
                totalAllServices += container.getServiceList().size();
                if (container.getSingleService() != null) {
                    totalAllServices++;
                }
            }

            writer.println("=".repeat(50));
            writer.println("ОБЩИЕ ИТОГИ:");
            writer.println("Всего услуг во всех контейнерах: " + totalAllServices);
            writer.println("Общая стоимость всех услуг: " + String.format("%.2f", totalAllCost) + " руб.");
            writer.println("Средняя стоимость услуги: " + String.format("%.2f", totalAllCost / totalAllServices) + " руб.");
            writer.println("=".repeat(50));

            System.out.println("Информация о контейнерах записана в файл: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
            return false;
        }
    }

    // Создает директорию и записывает статистику по типам услуг в отдельный файл
    public static boolean writeServiceTypesStatistics(List<Service> services, String basePath) {
        String statsDir = basePath + File.separator + "statistics";
        if (!createDataDirectory(statsDir)) {
            return false;
        }

        String statsFile = statsDir + File.separator + "service_types_stats.txt";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(statsFile)))) {

            writer.println("=== СТАТИСТИКА ПО ТИПАМ УСЛУГ ===");
            writer.println("Дата создания: " + new java.util.Date());
            writer.println("=".repeat(50));
            writer.println();

            int[] typeCounts = new int[ServiceType.values().length];
            double[] typeCosts = new double[ServiceType.values().length];

            for (Service service : services) {
                int typeIndex = service.getType().ordinal();
                typeCounts[typeIndex]++;
                typeCosts[typeIndex] += service.calculateTotalCost();
            }

            for (ServiceType type : ServiceType.values()) {
                int index = type.ordinal();
                writer.println("Тип: " + type.getDescription() + " (" + type.name() + ")");
                writer.println("Количество услуг: " + typeCounts[index]);
                writer.println("Общая стоимость: " + String.format("%.2f", typeCosts[index]) + " руб.");
                if (typeCounts[index] > 0) {
                    writer.println("Средняя стоимость: " + String.format("%.2f", typeCosts[index] / typeCounts[index]) + " руб.");
                }
                writer.println("-".repeat(40));
                writer.println();
            }

            System.out.println("Статистика записана в файл: " + statsFile);
            return true;

        } catch (IOException e) {
            System.out.println("Ошибка при записи статистики: " + e.getMessage());
            return false;
        }
    }

    // Проверяет существование файла и выводит его размер
    public static void checkFileInfo(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            System.out.println("Файл существует: " + filePath);
            System.out.println("Размер файла: " + file.length() + " байт");
            System.out.println("Последнее изменение: " + new java.util.Date(file.lastModified()));
        } else {
            System.out.println("Файл не существует: " + filePath);
        }
    }
}
