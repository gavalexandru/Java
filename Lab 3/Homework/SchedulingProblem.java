package Homework;

import java.util.*;

public class SchedulingProblem {
    private Airport airport;
    private List<Flight> flights;
    public SchedulingProblem(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }
    public Map<Flight,Runway> schedule(){
        List<Runway> runways = airport.getRunways();
        Map<Flight, Runway> schedule = new HashMap<Flight, Runway>();
        for(Runway runway : runways){
            Iterator<Flight> iterator = flights.iterator();
            while(iterator.hasNext()){
                Flight flight = iterator.next();
                if(runway.addFlight(flight)){
                    schedule.put(flight, runway);
                    iterator.remove();
                }
            }
        }
        return schedule;
    }
}
