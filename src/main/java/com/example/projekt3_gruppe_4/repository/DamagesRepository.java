package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Damages;
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
public class DamagesRepository {

    @Autowired
    DataSource dataSource;

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

    public List<Damages> getAllDamages() {
        String sql = "SELECT * FROM damage";
        List<Damages> damages = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                damages.add(new Damages(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damages;
    }

    public void deleteDamage(int damageId) {
        String sql = "DELETE FROM damage WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
