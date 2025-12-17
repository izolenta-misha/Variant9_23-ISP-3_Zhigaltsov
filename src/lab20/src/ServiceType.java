// ServiceType.java
public enum ServiceType {
    INTERNET(1, "Интернет-услуги"),
    DELIVERY(2, "Услуги доставки"),
    CLEANING(3, "Клининговые услуги");

    private final int id;
    private final String description;

    ServiceType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isValidType(String type) {
        for (ServiceType st : ServiceType.values()) {
            if (st.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}