package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    @GetMapping("/dataregistrering/ledige-biler")
    public String getAvailableCars(HttpSession session, Model model) {
        if (isUnauthorized(session, "dataregistrering/ledige-biler")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("cars", carService.getAllCars());
        return "ledige-biler";
    }

    @GetMapping("/skade-udbedring/tilbageleverede-biler")
    public String getReturnedCars(HttpSession session, Model model) {
        if (isUnauthorized(session, "skade-udbedring/tilbageleverede-biler")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("cars", carService.getReturnedCars());
        return "tilbageleverede-biler";
    }

    @GetMapping("/forretningsudvikler/udlejede-biler")
    public String getLeasedCars(HttpSession session, Model model) {
        if (isUnauthorized(session, "forretningsudvikler/udlejede-biler")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("cars", carService.getLeasedCars());
        return "udlejede-biler";
    }

    @GetMapping("/admin/opret-bil")
    public String getCreateCarForm(HttpSession session, Model model){
        if (isUnauthorized(session, "admin/opret-bil")) {
            return "redirect:/log-ind";
        }
        return "opret-bil";
    }

    @PostMapping("/admin/opret-bil")
    public String postCreateCar(@RequestParam String chassisNo,
                            @RequestParam String brand,
                            @RequestParam String model,
                            @RequestParam double purchasePrice,
                            HttpSession session,
                            Model Model) {
        if (isUnauthorized(session, "admin/opret-bil")) {
            return "redirect:/log-ind";
        }

        try {
            carService.createCar(chassisNo, brand, model, purchasePrice);
            return "redirect:/admin/opret-bil?success=true";
        } catch (IllegalArgumentException e) {
            Model.addAttribute("errorMessage", e.getMessage());
            Model.addAttribute("chassisNo", chassisNo);
            Model.addAttribute("brand", brand);
            Model.addAttribute("model", model);
            Model.addAttribute("purchasePrice", purchasePrice);
            return "opret-bil";
        }
    }
}
