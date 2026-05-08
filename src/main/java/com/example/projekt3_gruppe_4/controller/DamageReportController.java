package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.DamageReport;
import com.example.projekt3_gruppe_4.service.DamageReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DamageReportController {

    @Autowired
    DamageReportService damageReportService;

    @GetMapping("/showDamageReport")
    public String showDamageReport(Model model) {

        List<DamageReport> damageReportArraylist = damageReportService.getAllDamageReports();
        model.addAttribute("damageReports", damageReportArraylist);
        return "showDamageReport";
    }
}