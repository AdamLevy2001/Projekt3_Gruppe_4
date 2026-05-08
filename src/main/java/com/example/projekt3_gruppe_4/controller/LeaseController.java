package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.Car;
import com.example.projekt3_gruppe_4.model.Customer;
import com.example.projekt3_gruppe_4.model.DeliveryLocation;
import com.example.projekt3_gruppe_4.model.Lease;
import com.example.projekt3_gruppe_4.service.CarService;
import com.example.projekt3_gruppe_4.service.DeliveryLocationService;
import com.example.projekt3_gruppe_4.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LeaseController {
    @Autowired
    LeaseService leaseService;

    @Autowired
    CarService carService;

    @Autowired
    DeliveryLocationService deliveryLocationService;

    @GetMapping("/lease/create")
    public String showCreateLeaseForm(@RequestParam(required = false) Integer carId,
                                      Model model) {

        List<Car> carlist = carService.getAllCars();
        List<DeliveryLocation> locations = deliveryLocationService.getAllDeliveryLocations();

        model.addAttribute("cars", carlist);
        model.addAttribute("locations", locations);


        if (carId!=null) {
            Car selectedCar = carService.findCarById(carId);

            model.addAttribute("selectedCar", selectedCar);
        }

        return "create-lease";
    }


    @PostMapping("/lease/create")
    public String createLease(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phoneNumber,

            @RequestParam int carVehicleNo,
            @RequestParam int deliveryLocationId,

            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,

            @RequestParam double downPayment,
            @RequestParam double monthlyPayment,
            @RequestParam int kmPerMonth
    ) {

        Customer customer = new Customer();

        customer.setFirst_name(firstName);
        customer.setLast_name(lastName);
        customer.setEmail(email);
        customer.setPhone(phoneNumber);

        Lease lease = new Lease();

        lease.setCarVehicle_no(carVehicleNo);
        lease.setDeliveryLocation_id(deliveryLocationId);

        lease.setStart_date(startDate);
        lease.setEnd_date(endDate);

        lease.setDown_payment(downPayment);
        lease.setMonthly_payment(monthlyPayment);

        lease.setKm_per_month(kmPerMonth);

        leaseService.createLeaseWithCustomer(customer, lease);
        return "redirect:/lease/create";
    }
}
