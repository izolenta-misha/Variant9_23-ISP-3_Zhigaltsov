public class Main {
    public static void main(String[] args) {

        ConcreteService service1 = new ConcreteService(
                AbstractService.generateID(),
                "Обычная уборка",
                1500,
                2,
                0.1
        );


        ConcreteService specialService = new ConcreteService(
                AbstractService.generateID(),
                "Специальная уборка",
                1800,
                2.5,
                0.15
        ) {
            @Override
            public void applyDiscount() {
                super.applyDiscount();
                System.out.println("Дополнительная скидка 5% за срочность!");
            }
        };


        PremiumService premiumService = new PremiumService(
                AbstractService.generateID(),
                "Премиум-уборка VIP",
                2500,
                3,
                1.7
        ) {
            @Override
            public String toString() {
                return super.toString() + ", VIP-обслуживание";
            }
        };


        System.out.println("Обычный сервис:");
        System.out.println(service1);
        System.out.println("Стоимость: " + service1.calculateCost());
        service1.applyDiscount();

        System.out.println("\nСпециальный сервис:");
        System.out.println(specialService);
        System.out.println("Стоимость: " + specialService.calculateCost());
        specialService.applyDiscount();

        System.out.println("\nПремиум VIP сервис:");
        System.out.println(premiumService);
        System.out.println("Стоимость: " + premiumService.calculateCost());
        premiumService.applyDiscount();
    }
}
