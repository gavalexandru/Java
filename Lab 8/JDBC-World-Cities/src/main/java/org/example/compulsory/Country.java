package org.example.compulsory;

public class Country {
    private int id;
    private final String name;
    private final String code;
    private final Continent continent;

    public Country(String name, String code, Continent continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public Continent getContinent() {
        return continent;
    }
}
