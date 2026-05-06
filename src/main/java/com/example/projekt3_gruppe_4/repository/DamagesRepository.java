package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Damages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DamagesRepository {

    @Autowired
    private DataSource dataSource;

    public void saveDamage(Damages damages) {
        String sql = "INSERT INTO damages (damageReportID, description, price) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damages.getDamageReportID());
            statement.setString(2, damages.getDescription());
            statement.setDouble(3, damages.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
