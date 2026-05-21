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
    private LeaseService leaseService;

    @Autowired
    private CarService carService;

    @Autowired
    private DeliveryLocationService deliveryLocationService;

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    @GetMapping("/dataregistrering/opret-lejeaftale")
    public String getCreateLeaseForm(@RequestParam(required = false) Integer vehicleNo,
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
    public String postCreateLease(
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

            HttpSession session,
            Model model
    ) {

        if (isUnauthorized(session, "dataregistrering/opret-lejeaftale")) {
            return "redirect:/log-ind";
        }

        try {
            leaseService.createLeaseWithCustomer(
                    firstName, lastName, email, phoneNumber,
                    carVehicleNo, deliveryLocationId,
                    startDate, leaseLength,
                    downPayment, monthlyPayment, kmPerMonth);
            return "redirect:/dataregistrering/opret-lejeaftale?success=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("locations", deliveryLocationService.getAllDeliveryLocations());
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("email", email);
            model.addAttribute("phoneNumber", phoneNumber);
            model.addAttribute("carVehicleNo", carVehicleNo);
            model.addAttribute("deliveryLocationId", deliveryLocationId);
            model.addAttribute("startDate", startDate);
            model.addAttribute("leaseLength", leaseLength);
            model.addAttribute("downPayment", downPayment);
            model.addAttribute("monthlyPayment", monthlyPayment);
            model.addAttribute("kmPerMonth", kmPerMonth);
            return "opret-lejeaftale";
        }
    }
}
