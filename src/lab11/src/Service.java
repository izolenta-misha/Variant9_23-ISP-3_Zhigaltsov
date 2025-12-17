public class Service {
    private String name;
    private double time;
    private int price;
    private double sale;

    public Service() {
        this("чистка", 1, 200, 0);
    }
    public Service(String name, double time, int price, double sale) {
        setName(name);
        setTime(time);
        setPrice(price);
        setTime(time);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }
    public double getTime() {
        return time;
    }
    public void setTime(double time) {
        this.time = Math.max(1, time);
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = Math.max(200, price);
    }
    public double getSale() {
        return sale;
    }
    public void setSale(double sale) {
        this.sale = Math.max(0, Math.min(sale, 1));
    }
    public double calculateCost() {
        return time * price;
    }
    @Override
    public String toString() {
        return "Услуга: " + name +
                ", цена: " + price +
                ", длительность: " + time +
                ", скидка: " + sale;
    }
}
