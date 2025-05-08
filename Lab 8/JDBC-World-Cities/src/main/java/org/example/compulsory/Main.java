package org.example.compulsory;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        ContinentDAO continent = new ContinentDAO(connection);
        CountryDAO country = new CountryDAO(connection);
        Continent continent1 = new Continent("Europe");
        Country country1 = new Country("Romania","012",continent1);
        try {
            continent.createContinent(continent1);
            Continent copy1 = continent.findByName("Europe");
            Continent copy2 = continent.findById(999);
            if(copy1 == null) System.out.println("Not found by name!");
            else System.out.println("Found by name!");
            if(copy2 == null) System.out.println("Not found by id!");
            else System.out.println("Found by id!");
            country.createCountry(country1, continent1.getId());
            Country copyCountry1 = country.findCountryByName("Romania");
            Country copyCountry2 = country.findCountryById(999);
            if(copyCountry1 == null) System.out.println("Not found by name!");
            else System.out.println("Found by name!");
            if(copyCountry2 == null) System.out.println("Not found by id!");
            else System.out.println("Found by id!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnectionManager.getInstance().closeConnection();
        }
    }
}
