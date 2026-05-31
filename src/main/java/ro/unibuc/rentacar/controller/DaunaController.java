package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.ContractInchiriere;
import ro.unibuc.rentacar.entity.Dauna;
import ro.unibuc.rentacar.service.ContractInchiriereService;
import ro.unibuc.rentacar.service.DaunaService;

import java.util.List;

@Controller
@RequestMapping("/daune")
public class DaunaController {

    private final DaunaService service;
    private final ContractInchiriereService contractService;

    public DaunaController(DaunaService service, ContractInchiriereService contractService) {
        this.service = service;
        this.contractService = contractService;
    }

    @ModelAttribute("contracte")
    public List<ContractInchiriere> contracte() {
        return contractService.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("daune", service.findAll());
        return "daune/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("dauna", new Dauna());
        return "daune/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("dauna", service.findById(id));
        return "daune/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("dauna") Dauna dauna, BindingResult result) {
        if (result.hasErrors()) {
            return "daune/form";
        }
        service.save(dauna);
        return "redirect:/daune";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/daune";
    }
}