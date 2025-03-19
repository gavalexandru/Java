package Compulsory;

public class Freighter extends Aircraft implements CargoCapable {
    private int wingSpan;
    private double maximumPayload;
    private int batteryLife;
    public Freighter(String model, String name, String tailNumber, int id, int wingSpan, int maximumPayload , int batteryLife) {
        super(model, name, tailNumber, id);
        this.wingSpan = wingSpan;
        this.maximumPayload = maximumPayload;
        this.batteryLife = batteryLife;
    }
    public int getWingSpan() {
        return wingSpan;
    }
    @Override
    public double getMaximumPayload() {
        return maximumPayload;
    }
    public int getBatteryLife() {
        return batteryLife;
    }
}
