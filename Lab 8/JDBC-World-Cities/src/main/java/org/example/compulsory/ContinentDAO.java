package org.example.compulsory;

import java.sql.*;


public class ContinentDAO {
    private final Connection connection;

    public ContinentDAO(Connection connection) {
        this.connection = connection;
    }

    public void createContinent(Continent continent) throws SQLException {
        String sql = "INSERT INTO continents (name) VALUES (?)";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, continent.getName());
            statement.executeUpdate();
            try(ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    continent.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
    public Continent findByName(String name) throws SQLException {
        String sql = "SELECT * FROM continents WHERE name = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    Continent continent = new Continent(resultSet.getString("name"));
                    continent.setId(resultSet.getInt("id"));
                    return continent;
                }
            }
        }
        return null;
    }

    public Continent findById(int id) throws SQLException {
        String sql = "SELECT * FROM continents WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    Continent continent = new Continent(resultSet.getString("name"));
                    continent.setId(resultSet.getInt("id"));
                    return continent;
                }
            }
        }
        return null;
    }

}