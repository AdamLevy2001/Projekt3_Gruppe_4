package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.DamageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamageController {

    @Autowired
    private DamageService damageService;

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    @PostMapping("/skade-udbedring/skadesrapport/tilfoej-skade")
    public String postCreateDamage(@RequestParam String description,
                                   @RequestParam double price,
                                   @RequestParam int damageReportId,
                                   @RequestParam int vehicleNo,
                                   HttpSession session) {
        if (isUnauthorized(session, "skade-udbedring/skadesrapport")) {
            return "redirect:/log-ind";
        }
        damageService.registrerDamages(description, price, damageReportId);
        return "redirect:/skade-udbedring/skadesrapport?vehicleNo=" + vehicleNo;
    }

    @PostMapping("/skade-udbedring/skadesrapport/fjern-skade")
    public String postRemoveDamage(@RequestParam int damageId,
                                   @RequestParam int vehicleNo,
                                   HttpSession session) {
        if (isUnauthorized(session, "skade-udbedring/skadesrapport")) {
            return "redirect:/log-ind";
        }
        damageService.deleteDamage(damageId);
        return "redirect:/skade-udbedring/skadesrapport?vehicleNo=" + vehicleNo;
    }
}
