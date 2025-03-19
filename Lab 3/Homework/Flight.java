package Homework;
import Compulsory.*;
import java.time.LocalTime;
public class Flight {
private String flightId;
private LocalTime landingStart;
private LocalTime landingEnd;
private Aircraft aircraft;
public Flight(String flightId, LocalTime landingStart, LocalTime landingEnd, Aircraft aircraft) {
    this.flightId = flightId;
    this.landingStart = landingStart;
    this.landingEnd = landingEnd;
    this.aircraft = aircraft;
}
public boolean overlaps(Flight other) {
    if(landingStart.compareTo(other.landingEnd)>=0 || landingEnd.compareTo(other.landingStart)<=0) return false;
    else if(other.landingStart.compareTo(landingEnd)>=0 || other.landingEnd.compareTo(landingStart)<=0) return false;
    else return true;
}
public String getFlightId() {
    return flightId;
}
public LocalTime getLandingStart() {
    return landingStart;
}
public LocalTime getLandingEnd() {
    return landingEnd;
}
public Aircraft getAircraft() {
    return aircraft;
}
}
