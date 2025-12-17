import java.io.Serializable;

// Класс для хранения информации об услуге
class Service implements Serializable {
    private String id;
    private String name;
    private String category;
    private double price;
    private int duration; // в часах

    public Service(String id, String name, String category, double price, int duration) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (Категория: %s, Цена: %.2f, Длительность: %d ч)",
                id, name, category, price, duration);
    }
}