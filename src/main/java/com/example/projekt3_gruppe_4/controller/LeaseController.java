package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.*;
import com.example.projekt3_gruppe_4.service.CarService;
import com.example.projekt3_gruppe_4.service.DeliveryLocationService;
import com.example.projekt3_gruppe_4.service.LeaseService;
import jakarta.servlet.http.HttpSession;
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

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    @GetMapping("/dataregistrering/opret-lejeaftale")
    public String showCreateLeaseForm(@RequestParam(required = false) Integer vehicleNo,
                                      Model model,
                                      HttpSession session) {
        if (isUnauthorized(session, "dataregistrering/opret-lejeaftale")) {
            return "redirect:/log-ind";
        }

        List<Car> carlist = carService.getAllCars();
        List<DeliveryLocation> locations = deliveryLocationService.getAllDeliveryLocations();

        model.addAttribute("cars", carlist);
        model.addAttribute("locations", locations);

        if (vehicleNo != null) {
            Car selectedCar = carService.findCarById(vehicleNo);
            model.addAttribute("selectedCar", selectedCar);
        }

        return "opret-lejeaftale";
    }


    @PostMapping("/dataregistrering/opret-lejeaftale")
    public String createLease(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phoneNumber,

            @RequestParam int carVehicleNo,
            @RequestParam int deliveryLocationId,

            @RequestParam LocalDate startDate,
            @RequestParam int leaseLength,

            @RequestParam double downPayment,
            @RequestParam double monthlyPayment,
            @RequestParam int kmPerMonth,

            HttpSession session
    ) {

        if (isUnauthorized(session, "dataregistrering/opret-lejeaftale")) {
            return "redirect:/log-ind";
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phoneNumber);

        Lease lease = new Lease();
        lease.setCarVehicleNo(carVehicleNo);
        lease.setDeliveryLocationId(deliveryLocationId);
        lease.setStartDate(startDate);
        lease.setEndDate(startDate.plusMonths(leaseLength));
        lease.setDownPayment(downPayment);
        lease.setMonthlyPayment(monthlyPayment);
        lease.setKmPerMonth(kmPerMonth);
        lease.setStatus("active");

        leaseService.createLeaseWithCustomer(customer, lease);
        return "redirect:/dataregistrering/opret-lejeaftale?success=true";
    }
}
