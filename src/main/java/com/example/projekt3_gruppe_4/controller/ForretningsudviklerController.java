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
    public class ForretningsudviklerController {

        @Autowired
        private CarService carService;

        @GetMapping("/udlejede-biler")
        public String showLeasedCars(HttpSession session, Model model) {
            //User user = (User) session.getAttribute("loggedInUser");
            //if (user == null || !"FORRETNING".equals(user.getRole())) {    // matcher databasen og UserCreator
             //   return "redirect:/login";
            //F}
            model.addAttribute("cars", carService.getLeasedCars());
            return "udlejede-biler";
        }
    }

