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

    public List<Car> getAllCars(){
        return new ArrayList<>();
    }
}
