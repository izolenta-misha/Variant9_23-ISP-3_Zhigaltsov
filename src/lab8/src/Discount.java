public class Discount extends Service {
    private double maxSale = 0.3;
    private int salePrice = 10000;
    public Discount(String name, double time, int price, double sale) {
        super(name, time, price, sale);
    }
    @Override
    public double CalculateCost() {
        double baseCost = super.CalculateCost();
        double discount = Math.min(maxSale, getSale());
        if (baseCost > salePrice) {
            return baseCost * (1 - discount);
        } else {
            return baseCost;
        }
    }
    public void setMaxSale(double maxSale) {
        if (maxSale >= 0 && maxSale <= 1) {
            this.maxSale = Math.min(maxSale, 0.3);}
    }
    public double getMaxSale() {return maxSale;}
    public void setSalePrice(int salePrice) {
        if (salePrice > 0) {
            this.salePrice = salePrice;}
    }
    public int getSalePrice() {return salePrice;}
}
