package org.example.compulsory;

public record Location(String name, LocationType type) implements Comparable<Location> {
    public LocationType getType(){
        return type;
    }
    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }
    @Override
    public String toString() {
        return name;
    }
}
