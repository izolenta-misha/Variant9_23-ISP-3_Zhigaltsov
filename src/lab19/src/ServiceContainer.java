import java.util.ArrayList;
import java.util.List;

//Обобщенный контейнер для работы с услугами.

public class ServiceContainer<T extends Service> {
    private T singleService;  // Один объект услуги
    private List<T> serviceList;  // Список услуг
    private String containerName;

    //Конструктор для работы с одним объектом

    public ServiceContainer(String containerName, T service) {
        this.containerName = containerName;
        this.singleService = service;
        this.serviceList = new ArrayList<>();
    }

    //Конструктор для работы со списком объектов
    public ServiceContainer(String containerName, List<T> services) {
        this.containerName = containerName;
        this.serviceList = new ArrayList<>(services);
        this.singleService = services.isEmpty() ? null : services.get(0);
    }

    //Обычный метод (не generic) - добавляет услугу в список
    public void addService(T service) {
        if (service != null) {
            serviceList.add(service);
            System.out.println("Услуга добавлена в контейнер '" + containerName + "'");
        }
    }

    //Метод, явно использующий параметр типа <T> - возвращает услугу по индексу

    public T getServiceByIndex(int index) {
        if (index >= 0 && index < serviceList.size()) {
            return serviceList.get(index);
        }
        throw new IndexOutOfBoundsException("Индекс вне диапазона списка услуг");
    }

    //Обобщенный метод для расчета общей стоимости всех услуг в контейнере
    public double calculateTotalContainerCost() {
        double total = 0.0;
        for (T service : serviceList) {
            total += service.calculateTotalCost();
        }
        if (singleService != null) {
            total += singleService.calculateTotalCost();
        }
        return total;
    }

    //Метод для отображения информации о контейнере

    public void displayContainerInfo() {
        System.out.println("\n=== Информация о контейнере: " + containerName + " ===");
        System.out.println("Тип контейнера: " + (singleService != null ? "Одиночный объект" : "Список"));

        if (singleService != null) {
            System.out.println("\nЕдиничная услуга:");
            System.out.println(singleService);
        }

        if (!serviceList.isEmpty()) {
            System.out.println("\nСписок услуг (" + serviceList.size() + " шт.):");
            for (int i = 0; i < serviceList.size(); i++) {
                System.out.println("[" + i + "] " + serviceList.get(i).getType().getDescription() +
                        " - " + serviceList.get(i).getTariff());
            }
        }

        System.out.println("Общая стоимость всех услуг: " + calculateTotalContainerCost() + " руб.");
    }

    //Обобщенный метод для деактивации всех услуг в контейнере

    public void deactivateAllServices() {
        if (singleService != null) {
            singleService.setActive(false);
        }
        for (T service : serviceList) {
            service.setActive(false);
        }
        System.out.println("Все услуги в контейнере '" + containerName + "' деактивированы");
    }

    // Геттеры
    public T getSingleService() {
        return singleService;
    }

    public List<T> getServiceList() {
        return new ArrayList<>(serviceList);  // Возвращаем копию для безопасности
    }

    public String getContainerName() {
        return containerName;
    }

    //Метод для поиска услуг по типу (с использованием wildcard)
    public static void findServicesByType(List<? extends Service> services, ServiceType type) {
        System.out.println("\nПоиск услуг типа: " + type.getDescription());
        boolean found = false;

        for (Service service : services) {
            if (service.getType() == type) {
                System.out.println("Найдено: " + service.getTariff() + " - " + service.getPrice() + " руб.");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Услуги типа " + type.getDescription() + " не найдены");
        }
    }
}