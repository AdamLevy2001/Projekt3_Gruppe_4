package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Damage;
import com.example.projekt3_gruppe_4.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {

    @Autowired
    DamageRepository damagesRepository;

    public void registrerDamages(String description, double price, int damageReport_id) {

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tomt");
        }

        if (!description.matches("[a-zA-ZøæåÆØÅ ]+")) {
            throw new IllegalArgumentException("Beskrivelse må ikke indeholde tal");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Pris skal være et positivt tal");
        }
        Damage damages = new Damage(damageReport_id, description, price);
        damagesRepository.saveDamage(damages);
    }

    public List<Damage> getAllDamagesByReportId(int damageReport_id) {
        return damagesRepository.getAllDamagesByReportId(damageReport_id);
    }

    public void fjernDamage(int damageId) {
        damagesRepository.deleteDamage(damageId);
    }
}
