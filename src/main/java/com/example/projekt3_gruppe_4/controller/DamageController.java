package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.service.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamageController {

    @Autowired
    DamageService damagesService;

    @GetMapping("/createDamage")
    public String showDamage(Model model) {
        model.addAttribute("damages", damagesService.getDamages());
        return "createDamageReport";
    }

    @PostMapping("/createDamage")
    public String createDamage(@RequestParam String description,
                              @RequestParam double price) {
        damagesService.registrerDamages(description, price);
        return "redirect:/createDamage";
    }

    @PostMapping("/removeDamage")
    public String removeDamage(@RequestParam int damageId) {
        damagesService.fjernDamage(damageId);
        return "redirect:/createDamage";
    }

    @PostMapping("/fininshDamageReport")
    public String finishDamageReport() {
        return "redirect:/damageReport";
    }
}
