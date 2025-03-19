package Homework;
import Compulsory.*;

import java.util.ArrayList;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Aircraft[] aircrafts = {
                new Airliner("Boeing 737", "N12345", "TAIL737", 1, 180, 20000, 5,1),
                new Freighter("Boeing 747 Freighter", "CARGO1", "TAIL747", 2, 137000, 8,2),
                new Drone("DJI Cargo Drone", "DRN001", "DRNT001", 3, 20, 3,3)
        };
        Airport airport = new Airport(2);
        List<Flight> flights = new ArrayList<>();
                flights.add(new Flight("1A",LocalTime.of(10,0),LocalTime.of(10,30), aircrafts[0]));
                flights.add(new Flight("2B",LocalTime.of(10,30),LocalTime.of(11,0), aircrafts[1]));
                flights.add(new Flight("3C",LocalTime.of(10,30),LocalTime.of(11,0), aircrafts[2]));
        SchedulingProblem schedulingProblem = new SchedulingProblem(airport,flights);
        Map<Flight,Runway> schedule = schedulingProblem.schedule();
        for(Map.Entry<Flight,Runway> entry: schedule.entrySet()) {
            Flight flight = entry.getKey();
            Runway runway = entry.getValue();
            System.out.println("Flight: " + flight.getFlightId() + " on the runway: " + runway.getId());
        }
    }
}
