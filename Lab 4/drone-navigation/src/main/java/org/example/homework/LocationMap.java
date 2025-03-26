package org.example.homework;
import org.example.compulsory.*;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

public class LocationMap {
    private final Graph graph;
    private final Location[] locations;
    public LocationMap(Location[] locations) {
        this.locations = locations;
        graph = GraphBuilder.empty().buildGraph();
        for(int i=1;i<=locations.length;i++) graph.addVertex(i);
    }
    public void addEdge(int src, int dest, double weight){
        graph.addEdge(src, dest, weight);
    }
    public int[] findVertex(Location src, Location dest){
        int[] res = new int[2];
        for(int index=0;index<locations.length;index++){
            if(locations[index].compareTo(src) == 0) res[0] = index+1;
            else if(locations[index].compareTo(dest) == 0) res[1] = index+1;
        }
        return res;
    }
    public Graph getGraph(){
        return graph;
    }
    public Location[] getLocations(){
        return locations;
    }
}
