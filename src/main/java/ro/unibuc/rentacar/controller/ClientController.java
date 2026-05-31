package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.Client;
import ro.unibuc.rentacar.service.ClientService;

@Controller
@RequestMapping("/clienti")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clienti", service.findAll());
        return "clienti/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("client", new Client());
        return "clienti/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("client", service.findById(id));
        return "clienti/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("client") Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "clienti/form";
        }
        service.save(client);
        return "redirect:/clienti";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/clienti";
    }
}