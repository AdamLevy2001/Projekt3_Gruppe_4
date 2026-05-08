package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.repository.DamageReportRepository;
import com.example.projekt3_gruppe_4.repository.LeaseRepository;
import com.example.projekt3_gruppe_4.service.DamageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamageController {

    @Autowired
    DamageService damageService;

    @Autowired
    LeaseRepository leaseRepository;

    @Autowired
    DamageReportRepository damageReportRepository;

    @GetMapping("/createDamage")
    public String showDamage(@RequestParam int carId, Model model, HttpSession session) {
        int leaseId = leaseRepository.findLeaseIdByVehicleNo(carId);
        int damageReportId = damageReportRepository.getOrCreateDamageReport(leaseId);
        model.addAttribute("damages", damageService.getAllDamagesByReportId(damageReportId));
        model.addAttribute("vognnummer", carId);
        model.addAttribute("damageReportId", damageReportId);
        return "createDamageReport";
    }

    @PostMapping("/createDamage")
    public String createDamage(@RequestParam String description,
                               @RequestParam double price,
                               @RequestParam int damageReportId,
                               @RequestParam int carId) {
        System.out.println("damageReportId: " + damageReportId);
        System.out.println("description: " + description);
        System.out.println("price: " + price);
        damageService.registrerDamages(description, price, damageReportId);
        return "redirect:/createDamage?carId=" + carId;
    }

    @PostMapping("/removeDamage")
    public String removeDamage(@RequestParam int damageId,
                               @RequestParam int carId) {
        System.out.println("Sletter skade med id: " + damageId);
        damageService.fjernDamage(damageId);
        return "redirect:/createDamage?carId=" + carId;
    }

    @PostMapping("/fininshDamageReport")
    public String finishDamageReport() {
        return "redirect:/damageReport";
    }
}
