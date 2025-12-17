public class PremiumService extends BasicService {
    private double premiumMultiplier;

    public PremiumService(int id, String name, double basePrice, double duration, double premiumMultiplier) {
        super(id, name, basePrice, duration);
        this.premiumMultiplier = premiumMultiplier;
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() * premiumMultiplier;
    }

    @Override
    public void applyDiscount() {
        System.out.println("Премиум-услуги не имеют скидок");
    }

    @Override
    public String toString() {
        return getServiceInfo() + ", Длительность: " + duration +
                ", Базовая цена: " + basePrice + ", Премиум-множитель: " + premiumMultiplier;
    }
}
