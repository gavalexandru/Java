package org.example.compulsory;

import java.sql.*;

public class CountryDAO {
    private final Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public void createCountry(Country country, int continentId) throws SQLException {
        String sql = "INSERT INTO countries (name, code, continent_id) VALUES (?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, country.getName());
            statement.setString(2, country.getCode());
            statement.setInt(3, continentId);
            statement.executeUpdate();
            try(ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    country.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Country findCountryByName(String name) throws SQLException {
        String sql = "SELECT * FROM countries WHERE name = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String countryName = resultSet.getString(2);
                    String countryCode = resultSet.getString(3);
                    int continentId = resultSet.getInt(4);
                    ContinentDAO continentDAO = new ContinentDAO(connection);
                    Continent continent = continentDAO.findById(continentId);
                    Country country = new Country(countryName, countryCode, continent);
                    country.setId(id);
                    return country;
                }
            }
        }
        return null;
    }

    public Country findCountryById(int id) throws SQLException {
        String sql = "SELECT * FROM countries WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    String countryName = resultSet.getString(2);
                    String countryCode = resultSet.getString(3);
                    int continentId = resultSet.getInt(4);
                    ContinentDAO continentDAO = new ContinentDAO(connection);
                    Continent continent = continentDAO.findById(continentId);
                    Country country = new Country(countryName, countryCode, continent);
                    country.setId(id);
                    return country;
                }
            }
        }
        return null;
    }
}
