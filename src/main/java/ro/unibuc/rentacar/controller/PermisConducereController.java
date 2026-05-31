package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.Client;
import ro.unibuc.rentacar.entity.PermisConducere;
import ro.unibuc.rentacar.service.ClientService;
import ro.unibuc.rentacar.service.PermisConducereService;

import java.util.List;

@Controller
@RequestMapping("/permise")
public class PermisConducereController {

    private final PermisConducereService service;
    private final ClientService clientService;

    public PermisConducereController(PermisConducereService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @ModelAttribute("clienti")
    public List<Client> clienti() {
        return clientService.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("permise", service.findAll());
        return "permise/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("permis", new PermisConducere());
        return "permise/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("permis", service.findById(id));
        return "permise/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("permis") PermisConducere permis, BindingResult result) {
        if (result.hasErrors()) {
            return "permise/form";
        }
        service.save(permis);
        return "redirect:/permise";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/permise";
    }
}