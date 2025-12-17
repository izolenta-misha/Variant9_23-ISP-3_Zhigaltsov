public class TypeCastingDemo {
    public static void main(String[] args) {

        ConcreteService concrete = new ConcreteService(
                AbstractService.generateID(),
                "Обычная уборка",
                1500,
                2,
                0.1
        );

        PremiumService premium = new PremiumService(
                AbstractService.generateID(),
                "Премиум-уборка",
                2000,
                3,
                1.5
        );


        ServiceFeatures service1 = concrete;
        ServiceFeatures service2 = premium;


        System.out.println("Восходящее приведение:");
        service1.showDetails();
        service1.addReview("Отличный сервис!");

        service2.showDetails();
        service2.addReview("Премиум-качество!");


        if (service1 instanceof ConcreteService) {
            ConcreteService castedConcrete = (ConcreteService) service1;
            castedConcrete.applyDiscount();
        }

        // Попытка небезопасного приведения
        try {
            ConcreteService wrongCast = (ConcreteService) service2;
        } catch (ClassCastException e) {
            System.out.println("Ошибка приведения типов: " + e.getMessage());
        }


        if (service2 instanceof PremiumService) {
            PremiumService castedPremium = (PremiumService) service2;
            System.out.println("Итоговая стоимость: " + castedPremium.calculateCost());
        }
    }
}
