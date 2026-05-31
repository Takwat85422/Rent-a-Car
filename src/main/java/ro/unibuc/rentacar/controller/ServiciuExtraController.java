package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.ServiciuExtra;
import ro.unibuc.rentacar.service.ServiciuExtraService;

@Controller
@RequestMapping("/servicii")
public class ServiciuExtraController {

    private final ServiciuExtraService service;

    public ServiciuExtraController(ServiciuExtraService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", service.findAll(pageable));
        return "servicii/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("serviciu", new ServiciuExtra());
        return "servicii/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("serviciu", service.findById(id));
        return "servicii/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("serviciu") ServiciuExtra serviciu, BindingResult result) {
        if (result.hasErrors()) {
            return "servicii/form";
        }
        service.save(serviciu);
        return "redirect:/servicii";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/servicii";
    }
}
