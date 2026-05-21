package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    @GetMapping("/forretningsudvikler/dashboard")
    public String getDashboard(HttpSession session, Model model){
        if (isUnauthorized(session, "forretningsudvikler/dashboard")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("leasedCarsCount", dashboardService.countLeasedCars());
        model.addAttribute("totalPrice", dashboardService.getTotalPriceForLeasedCars());
        model.addAttribute("cars", dashboardService.getLeasedCars());
        return "dashboard";
    }
}
