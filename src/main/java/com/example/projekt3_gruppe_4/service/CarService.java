package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Car;
import com.example.projekt3_gruppe_4.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getReturnedCars() {
        return carRepository.findReturned();
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car findCarById(int vehicleNo) {
        return carRepository.findCarById(vehicleNo);
    }

    public List<Car> getLeasedCars() {
        return carRepository.findLeased();
    }

    public double getTotalPriceForLeasedCars() {
        return carRepository.getTotalPriceForLeasedCars();
    }

    public int countLeasedCars() {
        return carRepository.countLeasedCars();
    }
}
