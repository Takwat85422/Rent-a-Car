package ro.unibuc.rentacar.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.Autovehicul;
import ro.unibuc.rentacar.entity.CategorieAuto;
import ro.unibuc.rentacar.service.AutovehiculService;
import ro.unibuc.rentacar.service.CategorieAutoService;

import java.util.List;

@Controller
@RequestMapping("/autovehicule")
public class AutovehiculController {

    private final AutovehiculService service;
    private final CategorieAutoService categorieService;

    public AutovehiculController(AutovehiculService service, CategorieAutoService categorieService) {
        this.service = service;
        this.categorieService = categorieService;
    }

    // categoriile disponibile pentru dropdown - se adauga automat in model la fiecare request
    @ModelAttribute("categorii")
    public List<CategorieAuto> categorii() {
        return categorieService.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("autovehicule", service.findAll());
        return "autovehicule/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("autovehicul", new Autovehicul());
        return "autovehicule/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("autovehicul", service.findById(id));
        return "autovehicule/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("autovehicul") Autovehicul autovehicul,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "autovehicule/form";
        }
        service.save(autovehicul);
        return "redirect:/autovehicule";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/autovehicule";
    }
}
