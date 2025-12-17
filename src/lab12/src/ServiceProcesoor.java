public class ServiceProcesoor {

    public static void processServices(AbstractService... services) {
        for (AbstractService service : services) {
            System.out.println("\nОбрабатываем услугу:");
            System.out.println(service);

            double cost = service.calculateCost();
            System.out.println("Базовая стоимость: " + cost);

            System.out.println("------------------------");
        }
    }
}

