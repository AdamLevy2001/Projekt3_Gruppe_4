package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkadeController {

    @Autowired
    private CarService carService;

    @GetMapping("/tilbageleverede-biler")
    public String showReturnedCars(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("cars", carService.getReturnedCars());
        return "tilbageleverede-biler";
    }
}
