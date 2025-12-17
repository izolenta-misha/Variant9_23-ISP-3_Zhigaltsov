import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<Service> services = new LinkedList<>();

        services.add(new Service("обычная уборка", 2, 1500, 0.1));
        services.add(new Service("генеральная уборка", 4, 2000, 0.15));
        services.add(new Service("мойка окон", 1, 1200, 0));
        services.addFirst(new Service("химчистка", 3, 2500, 0.2));
        services.addLast(new Service("уборка после ремонта", 6, 3000, 0.25));

        System.out.println("Исходный список:");

        services.removeIf(service->service.getPrice() < 2000);
        printList(services);

        services.addFirst(new Service("экспресс-уборка", 1, 1000, 0));
        services.addLast(new Service("уборка квартиры", 3, 1800, 0.1));

        System.out.println("\nСписок после добавления:");
        printList(services);

        services.removeFirst();
        services.removeLast();

        Service first = services.getFirst();
        Service last = services.getLast();

        System.out.println("\nПервый элемент: " + first);
        System.out.println("Последний элемент: " + last);

        Service found = findFirstWithPriceGreaterThan(services, 2000);
        if (found != null) {
            System.out.println("\nНайден элемент с ценой > 2000: " + found);
        }

        removeAllWithPriceGreaterThan(services, 2000);

        System.out.println("\nСписок после удаления:");
        printList(services);

        System.out.println("\nСписок пуст? " + services.isEmpty());
        System.out.println("Размер списка: " + services.size());
    }

    private static void printList(LinkedList<Service> list) {
        for (Service service : list) {
            System.out.println(service);
        }
    }

    private static Service findFirstWithPriceGreaterThan(LinkedList<Service> list, int price) {
        for (Service service : list) {
            if (service.getPrice() > price) {
                return service;
            }
        }
        return null;
    }

    private static void removeAllWithPriceGreaterThan(LinkedList<Service> list, int price) {
        list.removeIf(service -> service.getPrice() > price);
    }
}
