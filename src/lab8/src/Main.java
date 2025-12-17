public class Main {
    public static void main(String[] args) {

        Service basicService = new Service("обычная уборка", 2, 1500, 0.1);
        System.out.println(basicService);
        System.out.println("Стоимость: " + basicService.CalculateCost());

        basicService.setPrice(100);
        System.out.println(basicService);

        PremiumService premiumService = new PremiumService("премиум уборка", 3, 2000, 0);
        System.out.println(premiumService);
        System.out.println("стоимость: " + premiumService.CalculateCost());
        premiumService.setPremiumtarrif(1.8);
        System.out.println("новая стоимость: " + premiumService.CalculateCost());


        Discount saleService = new Discount("уборка со скидкой", 4, 2500, 0.1);
        System.out.println(saleService);
        System.out.println("стоимость со скидкой: " + saleService.CalculateCost());


        saleService.setSalePrice(5000);
        System.out.println("новая стоимость: " + saleService.CalculateCost());
        }
    }

