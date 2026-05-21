package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.DamageReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamageReportController {

    @Autowired
    private DamageReportService damageReportService;

    private boolean isUnauthorized(HttpSession session, String page) {
        User user = (User) session.getAttribute("loggedInUser");
        return user == null || !user.hasAccess(page);
    }

    private void buildDamageReportModel(int vehicleNo, Model model) {
        model.addAttribute("damages", damageReportService.getDamagesByVehicleNo(vehicleNo));
        model.addAttribute("vehicleNo", vehicleNo);
        model.addAttribute("damageReportId", damageReportService.getDamageReportIdByVehicleNo(vehicleNo));
    }

    @GetMapping("/skade-udbedring/skadesrapport")
    public String getDamageReportSkade(@RequestParam int vehicleNo, Model model, HttpSession session) {
        if (isUnauthorized(session, "skade-udbedring/skadesrapport")) {
            return "redirect:/log-ind";
        }
        buildDamageReportModel(vehicleNo, model);
        model.addAttribute("canEdit", true);
        return "skadesrapport";
    }

    @GetMapping("/forretningsudvikler/skadesrapport")
    public String getDamageReportForretning(@RequestParam int vehicleNo, Model model, HttpSession session) {
        if (isUnauthorized(session, "forretningsudvikler/skadesrapport")) {
            return "redirect:/log-ind";
        }
        buildDamageReportModel(vehicleNo, model);
        model.addAttribute("canEdit", false);
        return "skadesrapport";
    }

    @GetMapping("/skade-udbedring/skadesrapporter")
    public String getDamageReportsSkade(Model model, HttpSession session) {
        if (isUnauthorized(session, "skade-udbedring/skadesrapporter")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("damageReports", damageReportService.getAllDamageReports());
        return "skadesrapporter";
    }

    @GetMapping("/forretningsudvikler/skadesrapporter")
    public String getDamageReportsForretning(Model model, HttpSession session) {
        if (isUnauthorized(session, "forretningsudvikler/skadesrapporter")) {
            return "redirect:/log-ind";
        }
        model.addAttribute("damageReports", damageReportService.getAllDamageReports());
        return "skadesrapporter";
    }
}