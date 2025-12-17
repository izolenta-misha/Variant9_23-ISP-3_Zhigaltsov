// 6. Перечисление типов услуг
enum ServiceCategory {
    INTERNET("Интернет", "IT-услуги"),
    DELIVERY("Доставка", "Логистические услуги"),
    CLEANING("Уборка", "Бытовые услуги"),
    CONSULTATION("Консультация", "Профессиональные услуги"),
    REPAIR("Ремонт", "Технические услуги");

    private final String name;
    private final String description;

    ServiceCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
