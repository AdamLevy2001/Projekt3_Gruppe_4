package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    @GetMapping("/dataregistrering/ledige-biler")
    public String showAvailableCars(HttpSession session, Model model) {
        if (isUnauthorized(session, "dataregistrering/ledige-biler")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("cars", carService.getAllCars());
        return "ledige-biler";
    }

    @GetMapping("/skade-udbedring/tilbageleverede-biler")
    public String showReturnedCars(HttpSession session, Model model) {
        if (isUnauthorized(session, "skade-udbedring/tilbageleverede-biler")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("cars", carService.getReturnedCars());
        return "tilbageleverede-biler";
    }

    @GetMapping("/forretningsudvikler/udlejede-biler")
    public String showLeasedCars(HttpSession session, Model model) {
        if (isUnauthorized(session, "forretningsudvikler/udlejede-biler")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("cars", carService.getLeasedCars());
        return "udlejede-biler";
    }
}
