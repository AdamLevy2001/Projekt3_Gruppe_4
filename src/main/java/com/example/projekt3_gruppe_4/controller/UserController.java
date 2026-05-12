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

    @GetMapping("/log-ind")
    public String loginPage() {
        return "log-ind";
    }

    @PostMapping("/log-ind")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {
        try {
            User user = userService.login(username, password);
            session.setAttribute("loggedInUser", user);
            return "redirect:" + user.getDefaultPage();
        } catch (IllegalArgumentException e) {
            model.addAttribute("username", username);
            model.addAttribute("errorMessage", e.getMessage());
            return "log-ind";
        }
    }

    @PostMapping("/log-ud")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/log-ind";
    }

    @GetMapping("/admin/opret-bruger")
    public String opretBrugerPage() {
        return "opret-bruger";
    }

    @PostMapping("/admin/opret-bruger")
    public String postOpretBruger(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("role") String role,
                                  Model model) {
        try {
            userService.registrerUser(username, password, role);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("role", role);
            model.addAttribute("errorMessage", e.getMessage());
            return "opret-bruger";
        }
    }
}
