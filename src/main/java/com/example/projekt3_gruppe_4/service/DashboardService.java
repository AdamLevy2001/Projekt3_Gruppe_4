package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    CarService carService;

    public List<Car> getLeasedCars() {
        return carService.getLeasedCars();
    }

    public int countLeasedCars() {
        return carService.countLeasedCars();
    }

    public double getTotalPriceForLeasedCars() {
        return carService.getTotalPriceForLeasedCars();
    }
}
