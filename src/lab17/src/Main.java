import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ GENERIC КЛАССА ===\n");

        // Создание услуг
        Service internet = new Service(
                ServiceType.INTERNET,
                "Базовый тариф",
                60,
                500.0
        );

        Service cleaning = new Service(
                ServiceType.CLEANING,
                "Премиум-клининг",
                180,
                2500.0
        );

        Service delivery = new Service(
                ServiceType.DELIVERY,
                "Экспресс доставка",
                45,
                800.0
        );

        Service internetPro = new Service(
                ServiceType.INTERNET,
                "Про тариф",
                120,
                900.0
        );

        // 1. Демонстрация типобезопасности с использованием ограничения <T extends Service>
        System.out.println("1. СОЗДАНИЕ КОНТЕЙНЕРОВ:");

        // Корректное использование - только объекты Service
        ServiceContainer<Service> singleContainer = new ServiceContainer<>("Одиночный контейнер", internet);
        System.out.println("Создан контейнер с одной услугой");

        // Создаем список услуг
        List<Service> servicesList = Arrays.asList(cleaning, delivery, internetPro);
        ServiceContainer<Service> listContainer = new ServiceContainer<>("Групповой контейнер", servicesList);
        System.out.println("Создан контейнер со списком услуг (" + servicesList.size() + " шт.)");

        // 2. Демонстрация работы методов
        System.out.println("\n2. РАБОТА С МЕТОДАМИ КОНТЕЙНЕРА:");

        // Добавление новой услуги
        listContainer.addService(new Service(ServiceType.DELIVERY, "Стандарт доставка", 60, 500.0));

        // Получение услуги по индексу (обобщенный метод)
        Service retrievedService = listContainer.getServiceByIndex(1);
        System.out.println("\nПолучена услуга по индексу 1:");
        System.out.println("Тариф: " + retrievedService.getTariff());
        System.out.println("Стоимость: " + retrievedService.calculateTotalCost() + " руб.");

        // 3. Отображение информации о контейнерах
        System.out.println("\n3. ИНФОРМАЦИЯ О КОНТЕЙНЕРАХ:");
        singleContainer.displayContainerInfo();
        listContainer.displayContainerInfo();

        // 4. Демонстрация деактивации услуг
        System.out.println("\n4. ДЕАКТИВАЦИЯ УСЛУГ:");
        singleContainer.deactivateAllServices();
        System.out.println("Статус единичной услуги: " +
                (singleContainer.getSingleService().isActive() ? "Активна" : "Неактивна"));

        // 5. Использование статического generic метода с wildcard
        System.out.println("\n5. ПОИСК УСЛУГ ПО ТИПУ (WILDCARD):");
        ServiceContainer.findServicesByType(listContainer.getServiceList(), ServiceType.INTERNET);
        ServiceContainer.findServicesByType(listContainer.getServiceList(), ServiceType.DELIVERY);

        // 6. Демонстрация полиморфизма с generics
        System.out.println("\n6. ПОЛИМОРФИЗМ С GENERICS:");

        // Создаем контейнер с разными типами услуг
        List<Service> mixedServices = Arrays.asList(internet, cleaning, delivery);
        ServiceContainer<Service> mixedContainer = new ServiceContainer<>("Смешанный контейнер", mixedServices);

        // Работаем с услугами через обобщенный интерфейс
        for (int i = 0; i < mixedContainer.getServiceList().size(); i++) {
            Service service = mixedContainer.getServiceByIndex(i);
            System.out.println("\nУслуга " + (i + 1) + ":");
            System.out.println("Тип: " + service.getType().getDescription());
            System.out.println("Тариф: " + service.getTariff());
            System.out.println("Общая стоимость: " + service.calculateTotalCost() + " руб.");
        }

        // 7. Расчет общей стоимости
        System.out.println("\n7. ОБЩАЯ СТОИМОСТЬ ВСЕХ КОНТЕЙНЕРОВ:");
        double totalCost = singleContainer.calculateTotalContainerCost() +
                listContainer.calculateTotalContainerCost() +
                mixedContainer.calculateTotalContainerCost();
        System.out.println("Общая стоимость всех услуг во всех контейнерах: " + totalCost + " руб.");

        System.out.println("\n=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
    }
}