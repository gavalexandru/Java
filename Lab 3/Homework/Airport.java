package Homework;
import Compulsory.*;
import java.util.ArrayList;
import java.util.List;
public class Airport {
private List<Runway> runways;
public Airport(int numberOfRunways){
    runways = new ArrayList<Runway>();
    for(int i=0;i<numberOfRunways;i++) {
        runways.add(new Runway(i+1));
    }
}
public List<Runway> getRunways() {
    return runways;
}
}
