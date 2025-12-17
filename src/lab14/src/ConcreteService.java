public class ConcreteService extends BasicService implements ServiceFeatures {
    private double discount;

    public ConcreteService(int id, String name, double basePrice, double duration, double discount) {
        super(id, name, basePrice, duration);
        this.discount = discount;
    }

    @Override
    public double calculateCost() {
        double baseCost = super.calculateCost();
        return baseCost * (1 - discount);
    }

    @Override
    public void applyDiscount() {
        if (discount > 0) {
            System.out.println("Скидка применена: " + (discount * 100) + "%");
        }
    }

    @Override
    public String toString() {
        return getServiceInfo() + ", Длительность: " + duration +
                ", Базовая цена: " + basePrice + ", Скидка: " + (discount * 100) + "%";
    }
    @Override
    public void showDetails() {
        System.out.println("Детали услуги: " + getServiceInfo());
    }
    @Override
    public void addReview(String review) {
        System.out.println("Отзыв об услуге добавлен : " + review);
    }

}
