package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.DamageReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DamageReportRepository {

    @Autowired
    private DataSource dataSource;

    public List<DamageReportView> getAllDamageReports() {
        /*Henter alle skadesrapporter med deres id, dato, tilknyttet bils mærke/model/vognnummer,
          samt navnet på den kunde der står på den tilhørende lejeaftale.
          Bruger JOIN på tværs af damageReports, leases, cars og customers for at undgå
          flere database-kald per skadesrapport.

          ON definerer hvordan tabellerne kobles sammen:
            damageReports kobles til leases via lease_id
            leases kobles til cars via carVehicle_no
            leases kobles til customers via customer_id
        */
        String sql = "SELECT damageReports.id, damageReports.lease_id, damageReports.created_at, " +
                "cars.brand, cars.model, cars.vehicle_no, " +
                "customers.first_name, customers.last_name " +
                "FROM damageReports " +
                "INNER JOIN leases ON damageReports.lease_id = leases.id " +
                "INNER JOIN cars ON leases.carVehicle_no = cars.vehicle_no " +
                "INNER JOIN customers ON customers.id = leases.customer_id";

        List<DamageReportView> damageReports = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DamageReportView view = new DamageReportView(
                        resultSet.getInt("id"),
                        resultSet.getInt("vehicle_no"),
                        resultSet.getDate("created_at").toLocalDate(),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
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

        try (Connection connection = dataSource.getConnection();
             PreparedStatement select = connection.prepareStatement(selectSql)) {
            select.setInt(1, leaseId);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

            try (PreparedStatement insert = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insert.setInt(1, leaseId);
                insert.setDate(2, Date.valueOf(LocalDate.now()));
                insert.executeUpdate();
                ResultSet keys = insert.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved oprettelse af skadesrapport", e);
        }
        throw new RuntimeException("Kunne ikke oprette skadesrapport");
    }
}
