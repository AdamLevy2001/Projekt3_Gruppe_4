package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping
    public String mainPage(HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            return "redirect:" + user.getDefaultPage();
        }
        return "redirect:/login";
    }
}
