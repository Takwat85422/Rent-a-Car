package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.Locatie;
import ro.unibuc.rentacar.service.LocatieService;

@Controller
@RequestMapping("/locatii")
public class LocatieController {

    private final LocatieService service;

    public LocatieController(LocatieService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", service.findAll(pageable));
        return "locatii/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("locatie", new Locatie());
        return "locatii/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("locatie", service.findById(id));
        return "locatii/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("locatie") Locatie locatie, BindingResult result) {
        if (result.hasErrors()) {
            return "locatii/form";
        }
        service.save(locatie);
        return "redirect:/locatii";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/locatii";
    }
}
