
// Service.java
public class Service {
    private ServiceType type;
    private String tariff;
    private int duration;
    private double price;
    private boolean isActive;

    public Service(ServiceType type, String tariff, int duration, double price) {
        this.type = type;
        this.tariff = tariff;
        this.duration = duration;
        this.price = price;
        this.isActive = true;
    }

    // Геттеры и сеттеры
    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        if (ServiceType.isValidType(type.name())) {  // Проверка валидности типа
            this.type = type;
        } else {
            throw new IllegalArgumentException("Неверный тип услуги");
        }
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration > 0) {
            this.duration = duration;
        } else {
            throw new IllegalArgumentException("Длительность должна быть положительной");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Методы расчета
    public double calculateTotalCost() {
        return price * (duration / 60.0);
    }

    @Override
    public String toString() {
        return String.format("Услуга: %s (%s)\n" +
                        "Тариф: %s\n" +
                        "Длительность: %d минут\n" +
                        "Стоимость: %.2f руб.\n" +
                        "Статус: %s",
                type.getDescription(), type.name(),
                tariff, duration, price,
                isActive ? "Активна" : "Неактивна");
    }
}
