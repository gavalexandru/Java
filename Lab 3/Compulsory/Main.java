package Compulsory;

public class Main {
    public static void main(String[] args) {
        Aircraft[] aircrafts = {
                new Airliner("Boeing 737", "N12345", "TAIL737", 1, 180, 20000, 5,1),
                new Freighter("Boeing 747 Freighter", "CARGO1", "TAIL747", 2, 137000, 8,2),
                new Drone("DJI Cargo Drone", "DRN001", "DRNT001", 3, 20, 3,3)
        };
    }
}
