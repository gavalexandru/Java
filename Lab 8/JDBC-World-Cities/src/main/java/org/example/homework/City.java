package org.example.homework;

public class City {
    private int id;
    private String name;
    private int countryId;
    boolean isCapital;
    private Double latitude;
    private Double longitude;

    public City(String name, int countryId, boolean isCapital, Double latitude, Double longitude) {
        this.name = name;
        this.countryId = countryId;
        this.isCapital = isCapital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getCountryId() {
        return countryId;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

}
