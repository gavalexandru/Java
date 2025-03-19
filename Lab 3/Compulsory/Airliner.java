package Compulsory;

public class Airliner extends Aircraft implements PassengerCapable, CargoCapable {
    private int wingSpan;
    private int passengers;
    private double maximumPayload;
    private int batteryLife;
    public Airliner(String model, String name, String tailNumber, int id, int wingSpan, int passengers, int maximumPayload, int batteryLife) {
        super(model, name, tailNumber,id);
        this.wingSpan = wingSpan;
        this.passengers = passengers;
        this.maximumPayload = maximumPayload;
        this.batteryLife = batteryLife;
    }
    public int getWingSpan() {
        return wingSpan;
    }
    @Override
    public int getPassengers() {
        return passengers;
    }
    @Override
    public double getMaximumPayload() {
        return maximumPayload;
    }
    public int getBatteryLife() {
        return batteryLife;
    }
}