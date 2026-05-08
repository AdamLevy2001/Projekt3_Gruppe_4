package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DamageReport;
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

    public List<DamageReport> getAllDamageReports() {
        String sql = "SELECT dr.id, dr.lease_id, dr.created_at, " +
                "c.brand, c.model, " +
                "CONCAT(cu.first_name, ' ', cu.last_name) AS customerName " +
                "FROM damageReports dr " +
                "INNER JOIN leases l ON dr.lease_id = l.id " +
                "INNER JOIN cars c ON l.carVehicle_no = c.vehicle_no " +
                "INNER JOIN customers cu ON l.customer_id = cu.id";
       List<DamageReport> damageReports = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DamageReport damageReport = new DamageReport(
                        resultSet.getInt("id"),
                        resultSet.getInt("lease_id"),
                        resultSet.getDate("created_at").toLocalDate(),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("customerName")

                );
                damageReports.add(damageReport);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved visning af skaderapport", e);
        }

        return damageReports;
    }
}
