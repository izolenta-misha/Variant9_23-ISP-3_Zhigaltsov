
public class Main {
    public static void main(String[] args) {
        // Создание услуг - порядок параметров: ServiceType, String tariff, int duration, double price
        Service internet = new Service(
                ServiceType.INTERNET,
                "Базовый тариф",    // тариф
                60,                 // продолжительность
                500.0               // цена
        );

        Service cleaning = new Service(
                ServiceType.CLEANING,
                "Премиум-клининг",  // тариф  
                180,                // продолжительность
                2500.0              // цена
        );

        // Демонстрация работы
        System.out.println("Созданные услуги:");
        System.out.println(internet);
        System.out.println("\n" + cleaning);

        // Изменение типа услуги
        cleaning.setType(ServiceType.DELIVERY);
        cleaning.setPrice(500.0);
        cleaning.setDuration(30);

        System.out.println("\nИзмененная услуга:");
        System.out.println(cleaning);

        // Проверка валидности типа
        System.out.println("\nПроверка типа: " +
                ServiceType.isValidType("CLEANING"));

        // Дополнительная демонстрация расчета стоимости
        System.out.println("\nРасчет стоимости услуг:");
        System.out.println("Интернет: " + internet.calculateTotalCost() + " руб.");
        System.out.println("Доставка: " + cleaning.calculateTotalCost() + " руб.");
    }
}

