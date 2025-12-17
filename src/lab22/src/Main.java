// 9. Демонстрационный класс
public class Main{

    public static void main(String[] args) {
        ServiceManager manager = new ServiceManager();

        System.out.println("=== Демонстрация обработки исключений в системе управления услугами ===\n");

        // Тест 1: Некорректные данные услуги
        System.out.println("Тест 1: Попытка создания услуги с некорректными данными");
        try {
            Service invalidService = new Service(
                    "", // Пустой ID
                    "С", // Слишком короткое название
                    ServiceCategory.INTERNET,
                    -100, // Отрицательная цена
                    0, // Нулевая длительность
                    "" // Пустой поставщик
            );
        } catch (InvalidServiceDataException e) {
            System.err.println("Ошибка валидации данных: " + e.getMessage());
        }

        // Тест 2: Корректное добавление услуг
        System.out.println("\nТест 2: Добавление корректных услуг");
        try {
            Service service1 = new Service("NET001", "Высокоскоростной интернет",
                    ServiceCategory.INTERNET, 1500.0, 720, "Ростелеком");
            manager.addService(service1);

            Service service2 = new Service("DEL001", "Экспресс-доставка",
                    ServiceCategory.DELIVERY, 500.0, 24, "СДЭК");
            manager.addService(service2);

            Service service3 = new Service("CLN001", "Генеральная уборка",
                    ServiceCategory.CLEANING, 3000.0, 6, "Чистый дом");
            manager.addService(service3);

        } catch (ServiceException e) {
            System.err.println("Ошибка при добавлении услуги: " + e.getMessage());
        }

        // Тест 3: Попытка дублирования услуги
        System.out.println("\nТест 3: Попытка добавления услуги с существующим ID");
        try {
            Service duplicateService = new Service("NET001", "Другой интернет",
                    ServiceCategory.INTERNET, 2000.0, 720, "МТС");
            manager.addService(duplicateService);
        } catch (ServiceException e) {
            System.err.println("Ошибка при добавлении: " + e.getMessage());
        }

        // Тест 4: Превышение лимита цены для категории
        System.out.println("\nТест 4: Проверка лимита цены для категории");
        try {
            // Добавим несколько дорогих услуг в одну категорию
            for (int i = 2; i <= 5; i++) {
                Service expensiveService = new Service(
                        "INT" + i,
                        "Премиум интернет " + i,
                        ServiceCategory.INTERNET,
                        15000.0, // Высокая цена
                        720,
                        "Провайдер " + i
                );
                manager.addService(expensiveService);
            }
        } catch (ServiceException e) {
            System.err.println("Ошибка при добавлении: " + e.getMessage());
        }

        // Тест 5: Работа с неактивной услугой
        System.out.println("\nТест 5: Деактивация услуги и попытка её использования");
        try {
            // Деактивируем услугу
            manager.deactivateService("DEL001");

            // Пытаемся найти деактивированную услугу
            Service found = manager.findService("DEL001");
            System.out.println("Найдена услуга: " + found.getName());
        } catch (ServiceInactiveException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (ServiceException e) {
            System.err.println("Другая ошибка: " + e.getMessage());
        }

        // Тест 6: Превышение общего лимита услуг
        System.out.println("\nТест 6: Попытка превышения общего лимита услуг");
        try {
            // Добавляем услуги до превышения лимита
            for (int i = 4; i <= 15; i++) {
                Service newService = new Service(
                        "SVC" + i,
                        "Тестовая услуга " + i,
                        ServiceCategory.CONSULTATION,
                        1000.0 * i,
                        10,
                        "Тестовый поставщик"
                );
                manager.addService(newService);
            }
        } catch (ServiceLimitExceededException e) {
            System.err.println("Превышен лимит: " + e.getMessage() +
                    " (текущее количество: " + e.getCurrentCount() +
                    ", максимальное: " + e.getMaxLimit() + ")");
        } catch (ServiceException e) {
            System.err.println("Другая ошибка: " + e.getMessage());
        }

        // Тест 7: Корректная работа с услугами
        System.out.println("\nТест 7: Корректные операции с услугами");
        try {
            // Поиск существующей услуги
            Service foundService = manager.findService("NET001");
            System.out.println("Успешно найдена услуга: " + foundService.getName());

            // Изменение цены с валидацией
            foundService.setPrice(1800.0);
            System.out.println("Цена услуги изменена на: " + foundService.getPrice());

            // Попытка установить некорректную цену
            foundService.setPrice(-100);

        } catch (InvalidServiceDataException e) {
            System.err.println("Ошибка изменения цены: " + e.getMessage());
        } catch (ServiceException e) {
            System.err.println("Ошибка при работе с услугой: " + e.getMessage());
        }

        // Вывод статистики
        System.out.println("\n=== Статистика системы ===");
        System.out.println("Всего услуг: " + manager.getAllServices().size());
        System.out.println("Активных услуг: " + manager.getActiveServices().size());
        System.out.printf("Общая выручка: %.2f%n", manager.calculateTotalRevenue());

        System.out.println("\n=== Список всех услуг ===");
        for (Service service : manager.getAllServices()) {
            System.out.println(service);
        }

        System.out.println("\n=== Программа завершена ===");
    }
}