package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.CategorieAuto;
import ro.unibuc.rentacar.service.CategorieAutoService;

@Controller
@RequestMapping("/categorii")
public class CategorieAutoController {

    private final CategorieAutoService service;

    public CategorieAutoController(CategorieAutoService service) {
        this.service = service;
    }

    // READ - lista
    @GetMapping
    public String list(Model model) {
        model.addAttribute("categorii", service.findAll());
        return "categorii/list";
    }

    // CREATE - formular gol
    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("categorie", new CategorieAuto());
        return "categorii/form";
    }

    // UPDATE - formular cu date existente
    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("categorie", service.findById(id));
        return "categorii/form";
    }

    // CREATE + UPDATE - salvare cu validare
    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("categorie") CategorieAuto categorie,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "categorii/form";   // re-afiseaza formularul cu mesajele de eroare
        }
        service.save(categorie);
        return "redirect:/categorii";
    }

    // DELETE
    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/categorii";
    }
}