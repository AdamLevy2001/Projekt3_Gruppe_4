package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DamageReport;
import com.example.projekt3_gruppe_4.model.DamageReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
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

    public List<DamageReportView> getAllDamageReports() {
        String damageReportSql = "SELECT dr.id, dr.lease_id, dr.created_at, " +
                "c.brand, c.model, c.vehicle_no " +
                "FROM damageReports dr " +
                "INNER JOIN leases l ON dr.lease_id = l.id " +
                "INNER JOIN cars c ON l.carVehicle_no = c.vehicle_no";

        String customerSql = "SELECT cu.first_name, cu.last_name " +
                "FROM customers cu " +
                "INNER JOIN leases l ON cu.id = l.customer_id " +
                "WHERE l.id = ?";

        List<DamageReportView> damageReports = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(damageReportSql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int leaseId = resultSet.getInt("lease_id");

                String firstName = "";
                String lastName = "";

                try (PreparedStatement customerStatement = connection.prepareStatement(customerSql)) {
                    customerStatement.setInt(1, leaseId);
                    ResultSet customerResult = customerStatement.executeQuery();
                    if (customerResult.next()) {
                        firstName = customerResult.getString("first_name");
                        lastName = customerResult.getString("last_name");
                    }
                }

                DamageReportView view = new DamageReportView(
                        resultSet.getInt("id"),
                        resultSet.getInt("vehicle_no"),
                        resultSet.getDate("created_at").toLocalDate(),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        firstName,
                        lastName
                );
                damageReports.add(view);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved visning af skaderapport", e);
        }

        return damageReports;
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
