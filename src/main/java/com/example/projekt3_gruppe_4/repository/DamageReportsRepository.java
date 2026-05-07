package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DamageReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DamageReportsRepository {

    @Autowired
    private DataSource dataSource;

    public void saveDamageReport(DamageReports damageReports) {
        String sql = "INSERT INTO damageReports (leasesID, created_at) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageReports.getLeasesID());
            statement.setDate(2, java.sql.Date.valueOf(damageReports.getCreated_at()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
