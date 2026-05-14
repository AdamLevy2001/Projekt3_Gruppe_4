package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Damage;
import com.example.projekt3_gruppe_4.model.DamageReportView;
import com.example.projekt3_gruppe_4.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    DamageReportRepository damageReportRepository;

    @Autowired
    LeaseService leaseService;

    @Autowired
    DamageService damageService;

    public List<DamageReportView> getAllDamageReports() {
        return damageReportRepository.getAllDamageReports();
    }

    public List<Damage> getDamagesByVehicleNo(int vehicleNo) {
        int leaseId = leaseService.findLeaseIdByVehicleNo(vehicleNo);
        int damageReportId = damageReportRepository.getOrCreateDamageReport(leaseId);
        return damageService.getAllDamagesByReportId(damageReportId);
    }

    public int getDamageReportIdByVehicleNo(int vehicleNo) {
        int leaseId = leaseService.findLeaseIdByVehicleNo(vehicleNo);
        return damageReportRepository.getOrCreateDamageReport(leaseId);
    }
}
