public class PremiumService extends Service{
    private double premiumtarrif = 1.5;

    public PremiumService(String name, double time, int price, double sale) {
        super(name, time,price , sale);
    }
    @Override
    public double CalculateCost(){
        return getTime() * getPrice() * premiumtarrif;
    }
    public void setPremiumtarrif(double premiumtarrif){
        this.premiumtarrif = Math.max(1, premiumtarrif);
    }

}
