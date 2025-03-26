package org.example.compulsory;

public record Route(Location from, Location to, int time, double safetyProbability, int directly) {
}
