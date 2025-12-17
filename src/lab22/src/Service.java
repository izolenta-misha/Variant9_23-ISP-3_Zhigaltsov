// 7. Класс услуги с валидацией
class Service {
    private String id;
    private String name;
    private ServiceCategory category;
    private double price;
    private int durationHours;
    private boolean isActive;
    private String provider;

    public Service(String id, String name, ServiceCategory category,
                   double price, int durationHours, String provider) throws InvalidServiceDataException {

        validateId(id);
        validateName(name);
        validateCategory(category);
        validatePrice(price);
        validateDuration(durationHours);
        validateProvider(provider);

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.durationHours = durationHours;
        this.provider = provider;
        this.isActive = true;
    }

    private void validateId(String id) throws InvalidServiceDataException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidServiceDataException("ID услуги не может быть пустым", "ID", id);
        }
        if (id.length() < 3 || id.length() > 20) {
            throw new InvalidServiceDataException("ID услуги должен быть от 3 до 20 символов", "ID", id);
        }
    }

    private void validateName(String name) throws InvalidServiceDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidServiceDataException("Название услуги не может быть пустым", "Название", name);
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new InvalidServiceDataException("Название услуги должно быть от 2 до 50 символов", "Название", name);
        }
    }

    private void validateCategory(ServiceCategory category) throws InvalidServiceDataException {
        if (category == null) {
            throw new InvalidServiceDataException("Категория услуги не может быть null", "Категория", "null");
        }
    }

    private void validatePrice(double price) throws InvalidServiceDataException {
        if (price <= 0) {
            throw new InvalidServiceDataException("Цена услуги должна быть положительной", "Цена", price);
        }
        if (price > 1000000) {
            throw new InvalidServiceDataException("Цена услуги не может превышать 1,000,000", "Цена", price);
        }
    }

    private void validateDuration(int duration) throws InvalidServiceDataException {
        if (duration <= 0) {
            throw new InvalidServiceDataException("Длительность услуги должна быть положительной", "Длительность", duration);
        }
        if (duration > 720) { // 30 дней * 24 часа
            throw new InvalidServiceDataException("Длительность услуги не может превышать 720 часов", "Длительность", duration);
        }
    }

    private void validateProvider(String provider) throws InvalidServiceDataException {
        if (provider == null || provider.trim().isEmpty()) {
            throw new InvalidServiceDataException("Поставщик услуги не может быть пустым", "Поставщик", provider);
        }
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getProvider() {
        return provider;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPrice(double price) throws InvalidServiceDataException {
        validatePrice(price);
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Услуга [ID: %s, Название: %s, Категория: %s, Цена: %.2f, Длительность: %d ч, Поставщик: %s, Активна: %s]",
                id, name, category.getName(), price, durationHours, provider, isActive ? "Да" : "Нет");
    }
}
