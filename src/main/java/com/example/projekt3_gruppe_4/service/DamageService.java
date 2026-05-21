package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Damage;
import com.example.projekt3_gruppe_4.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {

    @Autowired
    private DamageRepository damageRepository;

    public void registrerDamages(String description, double price, int damageReportId) {

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tomt");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Pris skal være et positivt tal");
        }
        Damage damages = new Damage(damageReportId, description, price);
        damageRepository.saveDamage(damages);
    }

    public List<Damage> getAllDamagesByReportId(int damageReportId) {
        return damageRepository.getAllDamagesByReportId(damageReportId);
    }

    public void deleteDamage(int damageId) {
        damageRepository.deleteDamage(damageId);
    }
}
