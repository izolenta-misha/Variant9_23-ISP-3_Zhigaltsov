public interface ServiceFeatures {
    void showDetails();

    default void addReview(String review) {
        System.out.println("Отзыв добавлен: " + review);
    }

    static void showServiceInfo() {
        System.out.println("Информация о сервисе доступна");
    }
}
