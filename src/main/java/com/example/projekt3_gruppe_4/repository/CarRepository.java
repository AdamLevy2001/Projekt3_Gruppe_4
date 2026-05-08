package com.example.projekt3_gruppe_4.repository;

import com.example.projekt3_gruppe_4.model.Car;
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
public class CarRepository {

    @Autowired
    DataSource dataSource;

    public List<Car> findReturned() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE status = 'returned'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Car car = new Car();
                car.setVehicle_no(rs.getInt("vehicle_no"));
                car.setChassis_no(rs.getString("chassis_no"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPurchase_price(rs.getDouble("purchase_price"));
                car.setStatus(rs.getString("status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved indlæsning af tilbageleverede biler", e);
        }
        return cars;
    }

    public Car findCarById(int carId) {
        String sql = "SELECT * FROM cars WHERE vehicle_no = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Car car = new Car(
                        rs.getInt("vehicle_no"),
                        rs.getString("chassis_no"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getDouble("purchase_price"),
                        rs.getString("status")
                );
                return car;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Bil blev ikke fundet i systemet!");
        }
        return null;
    }

    public List<Car> getAllCars() {
        String sql = "SELECT * FROM cars WHERE status = 'available'";
        List<Car> carList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt("vehicle_no"),
                        resultSet.getString("chassis_no"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getDouble("purchase_price"),
                        resultSet.getString("status"));

                carList.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente bil liste!");
        }
        return carList;
    }

    public List<Car> findLeased() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT vehicle_no, chassis_no, brand, model, purchase_price, status FROM cars WHERE status = 'leased'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Car car = new Car();
                car.setVehicle_no(rs.getInt("vehicle_no"));
                car.setChassis_no(rs.getString("chassis_no"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPurchase_price(rs.getDouble("purchase_price"));
                car.setStatus(rs.getString("status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fejl ved indlæsning af udlejede biler", e);
        }
        return cars;
    }
}
