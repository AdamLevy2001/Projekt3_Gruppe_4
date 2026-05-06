package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Damages;
import com.example.projekt3_gruppe_4.repository.DamagesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DamagesService {

    @Autowired
    DamagesRepository damagesRepository;

    public void registrerDamages(String description, double price, HttpSession session) {

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tomt");
        }

        if (!description.matches("[a-zA-ZøæåÆØÅ ]+")) {
            throw new IllegalArgumentException("Beskrivelse må ikke indeholde tal");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Pris skal være et positivt tal");
        }
        Damages damages = new Damages(description, price);
        damagesRepository.saveDamage(damages);
    }
}
