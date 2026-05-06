package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Customer;
import com.example.projekt3_gruppe_4.model.Lease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class CustomerRepository {

    @Autowired
    DataSource dataSource;

    public Customer createCustomer(Customer customer) {
        String sql = "INSERT INTO customers (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getFirst_name());
            statement.setString(2, customer.getLast_name());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                customer.setId(id);
            }
        } catch (SQLException e) { throw new RuntimeException("Databasefejl");
        }
        return customer;
    }
}
