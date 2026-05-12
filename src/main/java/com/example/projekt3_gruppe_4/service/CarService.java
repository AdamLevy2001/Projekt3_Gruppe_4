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
  
    public void createCar(String chassisNo, String brand, String model, double purchasePrice) {
        if (chassisNo == null || chassisNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Stelnr. må ikke være tomt");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Mærke må ikke være tomt");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model må ikke være tomt");
        }
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("Pris skal være et positivt tal");
        }

        Car car = new Car();
        car.setChassisNo(chassisNo.trim());
        car.setBrand(brand.trim());
        car.setModel(model.trim());
        car.setPurchasePrice(purchasePrice);
        car.setStatus("available");

        carRepository.saveCar(car);
    }
}
