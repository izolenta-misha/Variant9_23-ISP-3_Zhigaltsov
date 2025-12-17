public class PremiumService extends BasicService implements ServiceFeatures {
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
    @Override
    public void showDetails() {
        System.out.println("Премиум-детали услуги: " + getServiceInfo());
    }
    @Override
    public void addReview(String review) {
        System.out.println("Отзыв об премиальной услуге добавлен: " + review);
    }

}
