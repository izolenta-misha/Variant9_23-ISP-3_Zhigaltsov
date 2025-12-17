public class Main {
    public static void main(String[] args) {
        ConcreteService service1 = new ConcreteService(AbstractService.generateID(), "Обычная уборка", 1500, 2, 0.1);
        PremiumService service2 = new PremiumService(AbstractService.generateID(), "Премиум-уборка", 2000, 3, 1.5);

        ServiceFeatures serviceFeature1 = service1;
        ServiceFeatures serviceFeature2 = service2;

        serviceFeature1.showDetails();
        serviceFeature1.addReview("Отличная уборка!");
        ServiceFeatures.showServiceInfo();

        System.out.println("\n" + service2);
        serviceFeature2.showDetails();
        serviceFeature2.addReview("Премиальное качество!");
    }
}
