package org.example.compulsory;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
      Location[] locations = {
        new Location("Iasi", LocationType.friendly),
              new Location("Cluj Napoca", LocationType.friendly),
              new Location("Bucuresti", LocationType.enemy),
              new Location("Timisoara", LocationType.neutral),
              new Location("Brasov", LocationType.neutral),
              new Location("Vaslui", LocationType.enemy),
      };

        TreeSet<Location> friends = Arrays.stream(locations)
                .filter(location -> location.type() == LocationType.friendly)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Friendly locations: " + friends);
        LinkedList<Location> enemies = Arrays.stream(locations)
                .filter(loc -> loc.type() == LocationType.enemy)
                .sorted(Comparator.comparing(Location::type)
                        .thenComparing(Location::name))
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Enemy locations: " + enemies);
    }
}