package org.example.homework;
import com.github.javafaker.Faker;
import org.example.compulsory.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Location[] locations = {
                new Location(faker.address().city(), LocationType.friendly),
                new Location(faker.address().city(), LocationType.friendly),
                new Location(faker.address().city(), LocationType.enemy),
                new Location(faker.address().city(), LocationType.neutral),
                new Location(faker.address().city(), LocationType.neutral),
                new Location(faker.address().city(), LocationType.enemy),
        };
        LocationMap locationMap = new LocationMap(locations);
        locationMap.addEdge(1, 2, 15);
        locationMap.addEdge(1, 3, 25);
        locationMap.addEdge(1, 4, 20);
        locationMap.addEdge(1, 5, 30);
        locationMap.addEdge(1, 6, 35);
        locationMap.addEdge(2, 3, 8);
        locationMap.addEdge(2, 4, 5);
        locationMap.addEdge(3, 6, 7);
        locationMap.addEdge(4, 5, 4);
        locationMap.addEdge(5, 6, 6);
        Robot drone = new Robot(locations[0], locationMap);
        drone.fastestRoutesFromStartToAll();
        Map<LocationType, List<Location>> map = Arrays.stream(locations).collect(Collectors.groupingBy(Location::getType));
        map.forEach((type, location) -> {
            System.out.println(type + " locations:");
            List<String> locationNames = location.stream()
                    .map(Location::toString)
                    .toList();
            for (int i = 0; i < locationNames.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + locationNames.get(i));
            }
        });
    }
}
