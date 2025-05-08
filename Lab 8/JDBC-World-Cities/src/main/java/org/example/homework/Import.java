package org.example.homework;
import org.example.compulsory.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Import {
    List<String> words = new ArrayList<>();

    public Import(String path, DatabaseConnectionPool dataSource) {
        processFileData(path, dataSource);
    }

    private void processFileData(String path, DatabaseConnectionPool dataSource) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                String cleanLine = line.replaceAll("\\s+", "");
                if (!cleanLine.isEmpty()) {
                    words.add(cleanLine);
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        importData(dataSource);
    }
    private void importData(DatabaseConnectionPool dataSource) {
        int i=0;
        String countryName = null;
        String capitalName = null;
        double capitalLatitude=0;
        double capitalLongitude=0;
        String countryCode = null;
        String continentName;
        boolean isCapital = true;
        for (String word : words) {
            i++;
            switch(i%6){
                case 0:
                    continentName = word;
                    try{
                        Connection connection = dataSource.getConnection();
                        ContinentDAO continentDAO = new ContinentDAO(connection);
                        Continent continent = new Continent(continentName);
                        if(continentDAO.findByName(continentName) == null) continentDAO.createContinent(continent);
                        else continent = continentDAO.findByName(continentName);
                        CountryDAO countryDAO = new CountryDAO(connection);
                        Country country = new Country(countryName,countryCode,continent);
                        if(countryDAO.findCountryByName(countryName) == null)countryDAO.createCountry(country,continent.getId());
                        else country = countryDAO.findCountryByName(countryName);
                        CityDAO cityDAO = new CityDAO(dataSource.getDataSource());
                        City city = new City(capitalName,country.getId(),isCapital,capitalLatitude,capitalLongitude);
                        cityDAO.createCity(city);
                    }
                    catch(SQLException e){
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    countryName = word;
                    break;
                case 2:
                    capitalName = word;
                    break;
                case 3:
                    capitalLatitude = Double.parseDouble(word);
                    break;
                case 4:
                    capitalLongitude = Double.parseDouble(word);
                    break;
                case 5:
                    countryCode = word;
                    break;
            }
        }
    }
}