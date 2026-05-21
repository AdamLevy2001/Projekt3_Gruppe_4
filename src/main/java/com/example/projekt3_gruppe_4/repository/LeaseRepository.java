package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Lease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class LeaseRepository {
    @Autowired
    private DataSource dataSource;

    public void createLease(Lease lease) {
        String insertLeaseSql = "INSERT INTO leases (carVehicle_no, customer_id, deliveryLocation_id, down_payment, monthly_payment, km_per_month, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String updateCarSql = "UPDATE cars SET status ='leased' WHERE vehicle_no = ?";

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement insertStatement = connection.prepareStatement(insertLeaseSql);
                    PreparedStatement updateStatement = connection.prepareStatement(updateCarSql);
            ) {

                insertStatement.setInt(1, lease.getCarVehicleNo());
                insertStatement.setInt(2, lease.getCustomerId());
                insertStatement.setInt(3, lease.getDeliveryLocationId());
                insertStatement.setDouble(4, lease.getDownPayment());
                insertStatement.setDouble(5, lease.getMonthlyPayment());
                insertStatement.setInt(6, lease.getKmPerMonth());
                insertStatement.setDate(7, Date.valueOf(lease.getStartDate()));
                insertStatement.setDate(8, Date.valueOf(lease.getEndDate()));
                insertStatement.setString(9, lease.getStatus());

                insertStatement.executeUpdate();

                updateStatement.setInt(1, lease.getCarVehicleNo());
                updateStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Fejl ved oprettelsen af lejeaftale!", e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database fejl", e);
        }
    }

    public int findLeaseIdByVehicleNo(int vehicleNo) {
        String sql = "SELECT id FROM leases WHERE carVehicle_no = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicleNo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database fejl ved hentning af lejeaftale", e);
        }

        throw new RuntimeException("Ingen lejeaftale fundet for bil: " + vehicleNo);
    }
}
