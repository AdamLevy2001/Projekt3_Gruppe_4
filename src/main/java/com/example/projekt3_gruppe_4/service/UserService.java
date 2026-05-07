package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Brugernavn og adgangskode må ikke være tomme!");
        }

        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("Brugernavn eller adgangskode er forkert!");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Brugernavn eller adgangskode er forkert!");
        }

        return user;
    }
}
