package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.ContractInchiriere;
import ro.unibuc.rentacar.entity.Plata;
import ro.unibuc.rentacar.service.ContractInchiriereService;
import ro.unibuc.rentacar.service.PlataService;

import java.util.List;

@Controller
@RequestMapping("/plati")
public class PlataController {

    private final PlataService service;
    private final ContractInchiriereService contractService;

    public PlataController(PlataService service, ContractInchiriereService contractService) {
        this.service = service;
        this.contractService = contractService;
    }

    @ModelAttribute("contracte")
    public List<ContractInchiriere> contracte() {
        return contractService.findAll();
    }

    @GetMapping
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", service.findAll(pageable));
        return "plati/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("plata", new Plata());
        return "plati/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("plata", service.findById(id));
        return "plati/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("plata") Plata plata, BindingResult result) {
        if (result.hasErrors()) {
            return "plati/form";
        }
        service.save(plata);
        return "redirect:/plati";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/plati";
    }
}
