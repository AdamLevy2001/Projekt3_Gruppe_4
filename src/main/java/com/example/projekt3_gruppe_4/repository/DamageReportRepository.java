package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DamageReportRepository {

    @Autowired
    private DataSource dataSource;

    public void saveDamageReport(DamageReport damageReports) {
        String sql = "INSERT INTO damageReports (lease_id, created_at) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageReports.getLease_id());
            statement.setDate(2, java.sql.Date.valueOf(damageReports.getCreated_at()));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved gemning af skadesrapport", e);
        }
    }
}
