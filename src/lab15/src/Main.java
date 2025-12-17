public class Main {
    public static void main(String[] args) {

        ConcreteService service1 = new ConcreteService(AbstractService.generateID(), "Обычная уборка", 1500, 2, 0.1);
        PremiumService service2 = new PremiumService(AbstractService.generateID(), "Премиум-уборка", 2000, 3, 1.5);

        System.out.println(service1);
        System.out.println("Стоимость: " + service1.calculateCost());
        service1.applyDiscount();

        System.out.println("\n" + service2);
        System.out.println("Стоимость: " + service2.calculateCost());
        service2.applyDiscount();
    }
}
