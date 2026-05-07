package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import com.example.projekt3_gruppe_4.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {
        try {
            User user = userService.login(username, password);
            session.setAttribute("loggedInUser",user);
            return "redirect:" + user.getDefaultPage();
        } catch (IllegalArgumentException e) {
            model.addAttribute("username", username);
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }
}
