package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DeliveryLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeliveryLocationRepository {
    @Autowired
    DataSource dataSource;

    public List<DeliveryLocation> getAllDeliveryLocations() {
        List<DeliveryLocation> deliveryLocations = new ArrayList<>();
        String sql = "SELECT * FROM deliveryLocations";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DeliveryLocation deliveryLocation = new DeliveryLocation(
                        resultSet.getInt("id"),
                        resultSet.getString("address"),
                        resultSet.getString("name")
                );
                deliveryLocations.add(deliveryLocation);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database fejl", e);
        }
        return deliveryLocations;
    }
}
