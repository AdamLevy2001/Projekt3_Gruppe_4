package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.DamageReport;
import com.example.projekt3_gruppe_4.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    DamageReportRepository damageReportRepository;

    public List<DamageReport> getAllDamageReports() {
        return damageReportRepository.getAllDamageReports();
    }
}
