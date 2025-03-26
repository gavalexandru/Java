package org.example.homework;
import org.example.compulsory.*;
import org.graph4j.Graph;
import org.graph4j.shortestpath.DijkstraShortestPathDefault;
public class Robot {
    private Location currentLocation;
    private final LocationMap locationMap;
    public Robot(Location startLocation, LocationMap locationMap) {
        this.currentLocation = startLocation;
        this.locationMap = locationMap;
    }
    public Location getCurrentLocation() {
        return currentLocation;
    }
    public void moveTo(Location destination, boolean check) {
        int[] vertex = locationMap.findVertex(currentLocation,destination);
            Graph graph = locationMap.getGraph();
            DijkstraShortestPathDefault dijkstra = new DijkstraShortestPathDefault(graph,vertex[0]);
            if(dijkstra.findPath(vertex[1])==null){
                System.out.println("Nu exista un drum de la: " + currentLocation + " la: " + destination);
            }
            else{
                double time = dijkstra.getPathWeight(vertex[1]);
                System.out.println("Cel mai rapid drum de la: " + currentLocation + " la: " + destination + " dureaza: " + time + " minute.");
                if(check) currentLocation = destination;
            }
    }
    public void fastestRoutesFromStartToAll(){
        for(Location location : locationMap.getLocations()){
            if(location.getType() == LocationType.friendly && location.compareTo(currentLocation)!=0) moveTo(location, false);
        }
        for(Location location : locationMap.getLocations()){
            if(location.getType() == LocationType.neutral && location.compareTo(currentLocation)!=0) moveTo(location, false);
        }
        for(Location location : locationMap.getLocations()){
            if(location.getType() == LocationType.enemy && location.compareTo(currentLocation)!=0) moveTo(location, false);
        }
    }
}
