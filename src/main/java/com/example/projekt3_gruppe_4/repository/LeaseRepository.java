package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Lease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class LeaseRepository {
    @Autowired
    DataSource dataSource;

    public void createLease(Lease lease) {
        String insertLeaseSql = "INSERT INTO leases (carVehicle_no, customer_id, deliveryLocation_id, down_payment, monthly_payment, km_per_month, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String updateCarSql = "UPDATE cars SET status ='leased' WHERE vehicle_no = ?";

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement insertStatement = connection.prepareStatement(insertLeaseSql);
                    PreparedStatement updateStatement = connection.prepareStatement(updateCarSql);
            ) {

                insertStatement.setInt(1, lease.getCarVehicle_no());
                insertStatement.setInt(2, lease.getCustomer_id());
                insertStatement.setInt(3, lease.getDeliveryLocation_id());
                insertStatement.setDouble(4, lease.getDown_payment());
                insertStatement.setDouble(5, lease.getMonthly_payment());
                insertStatement.setInt(6, lease.getKm_per_month());
                insertStatement.setDate(7, Date.valueOf(lease.getStart_date()));
                insertStatement.setDate(8, Date.valueOf(lease.getEnd_date()));
                insertStatement.setString(9, lease.getStatus());

                insertStatement.executeUpdate();

                updateStatement.setInt(1, lease.getCarVehicle_no());
                updateStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("fejl ved oprettelsen af lejeaftale!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database fejl");
        }
    }

    public int findLeaseIdByVehicleNo(int vehicle_no) {
        String sql = "SELECT id FROM leases WHERE carVehicle_no = ? AND status = 'active'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicle_no);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database fejl ved hentning af lejeaftale", e);
        }

        throw new RuntimeException("Ingen aktiv lejeaftale fundet for bil: " + vehicle_no);
    }
}
