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
    @GetMapping("/ledige-biler")
    public String showAvailableCars(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("cars", carService.getAllCars());
        return "ledige-biler";
    }
}
