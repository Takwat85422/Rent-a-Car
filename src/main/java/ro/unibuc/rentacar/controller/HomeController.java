package ro.unibuc.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/acces-interzis")
    public String accesInterzis(Model model) {
        model.addAttribute("titlu", "Acces interzis");
        model.addAttribute("mesaj", "Nu ai drepturile necesare. Doar un ADMIN poate face aceasta actiune.");
        return "eroare";
    }
}