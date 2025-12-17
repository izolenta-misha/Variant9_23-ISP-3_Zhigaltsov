public abstract class AbstractService {
    protected int id;
    protected String name;
    protected double basePrice;

    public AbstractService(int id, String name, double basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    public abstract double calculateCost();

    public String getServiceInfo() {
        return "ID: " + id + ", Название: " + name;
    }

    public static int generateID() {
        return (int) (Math.random() * 1000);
    }
    public String getFullInfo() {
        return getServiceInfo() + ", Базовая цена: " + basePrice;
    }
}
