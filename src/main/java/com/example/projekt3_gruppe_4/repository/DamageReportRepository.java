package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

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

    public int getOrCreateDamageReport(int leaseId) {
        String selectSql = "SELECT id FROM damageReports WHERE lease_id = ?";
        String insertSql = "INSERT INTO damageReports (lease_id, created_at) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement select = connection.prepareStatement(selectSql);
            select.setInt(1, leaseId);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            PreparedStatement insert = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setInt(1, leaseId);
            insert.setDate(2, Date.valueOf(LocalDate.now()));
            insert.executeUpdate();
            ResultSet keys = insert.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved oprettelse af skadesrapport", e);
        }
        throw new RuntimeException("Kunne ikke oprette skadesrapport");
    }
}
