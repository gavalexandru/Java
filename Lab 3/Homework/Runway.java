package Homework;
import Compulsory.*;
import java.util.ArrayList;
import java.util.List;
public class Runway {
private int id;
private List<Flight> scheduledFlights;
public Runway(int id) {
    this.id = id;
    scheduledFlights = new ArrayList<>();
}
public boolean canScheduleFlight(Flight flight) {
    for(Flight scheduledFlight : scheduledFlights) {
        if(scheduledFlight.overlaps(flight)) return true;
    }
    return false;
}
public boolean addFlight(Flight flight) {
    boolean ok = true;
    for(Flight scheduledFlight : scheduledFlights) {
        if(scheduledFlight.overlaps(flight)) ok = false;
    }
    if(ok){
        scheduledFlights.add(flight);
    }
    return ok;
}
public int getId() {
    return id;
}
public List<Flight> getScheduledFlights() {
    return scheduledFlights;
}
}
