package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.Damages;
import com.example.projekt3_gruppe_4.service.DamagesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamagesController {

    @Autowired
    DamagesService damagesService;

    @GetMapping("/createDamage")
    public String showDamage(Model model, HttpSession session) {
        model.addAttribute("damages", damagesService.getDamages());
        return "createDamageReport";
    }

    @PostMapping("/createDamage")
    public String createDamage(@RequestParam String description,
                              @RequestParam double price,
                              HttpSession session) {
        damagesService.registrerDamages(description, price, session);
        return "redirect:/createDamage";
    }

    @PostMapping("/removeDamage")
    public String removeDamage(@RequestParam int damageId, HttpSession session) {
        damagesService.fjernDamage(damageId);
        return "redirect:/createDamage";
    }

    @PostMapping("/fininshDamageReport")
    public String finishDamageReport(HttpSession session) {
        return "redirect:/damageReport";
    }
}
