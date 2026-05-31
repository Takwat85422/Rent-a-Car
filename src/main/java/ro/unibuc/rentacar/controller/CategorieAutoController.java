package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", service.findAll(pageable));
        return "categorii/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("categorie", new CategorieAuto());
        return "categorii/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("categorie", service.findById(id));
        return "categorii/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("categorie") CategorieAuto categorie, BindingResult result) {
        if (result.hasErrors()) {
            return "categorii/form";
        }
        service.save(categorie);
        return "redirect:/categorii";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/categorii";
    }
}
