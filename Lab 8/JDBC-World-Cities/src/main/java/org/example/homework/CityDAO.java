package org.example.homework;

import java.sql.*;

import com.zaxxer.hikari.HikariDataSource;


public class CityDAO {
    private HikariDataSource dataSource;

    public CityDAO(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createCity(City city) throws SQLException {
        String sql = "INSERT INTO cities (name, country_id, is_capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getCountryId());
            statement.setBoolean(3, city.isCapital());
            statement.setDouble(4, city.getLatitude());
            statement.setDouble(5, city.getLongitude());
            statement.executeUpdate();
            try(ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

}
