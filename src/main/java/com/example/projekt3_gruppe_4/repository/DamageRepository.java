package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Damage;
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
public class DamageRepository {

    @Autowired
    DataSource dataSource;

    public void saveDamage(Damage damage) {
        String sql = "INSERT INTO damages (damageReport_id, description, price) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damage.getDamageReportID());
            statement.setString(2, damage.getDescription());
            statement.setDouble(3, damage.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved gemning af skade", e);
        }
    }

    public List<Damage> getAllDamagesByReportId(int damageReportId) {
        String sql = "SELECT * FROM damages WHERE damageReport_id = ?";
        List<Damage> damages = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageReportId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                damages.add(new Damage(
                        resultSet.getInt("id"), damageReportId,
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved hentning af skader", e);
        }
        return damages;
    }

    public void deleteDamage(int damageId) {
        String sql = "DELETE FROM damages WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved sletning af skade med id: " + damageId, e);
        }
    }
}
