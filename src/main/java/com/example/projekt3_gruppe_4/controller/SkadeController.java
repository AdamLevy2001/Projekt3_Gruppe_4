package com.example.projekt3_gruppe_4.controller;

import com.example.projekt3_gruppe_4.service.CarService;

    @Controller
    @RequestMapping("/skade")
    public class SkadeController {

        @Autowired
        private CarService carService;

        @GetMapping("/tilbageleverede-biler")
        public String showReturnedCars(HttpSession session, Model model) {
            User user = (User) session.getAttribute("user");
            if (user == null || !"SKADE".equals(user.getRole())) {
                return "redirect:/login";
            }
            model.addAttribute("cars", carService.getReturnedCars());
            return "skade/tilbageleverede-biler";
        }
    }
