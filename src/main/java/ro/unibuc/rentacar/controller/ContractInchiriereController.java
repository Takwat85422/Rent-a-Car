package ro.unibuc.rentacar.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.rentacar.entity.*;
import ro.unibuc.rentacar.service.*;

import java.util.List;

@Controller
@RequestMapping("/contracte")
public class ContractInchiriereController {

    private final ContractInchiriereService service;
    private final ClientService clientService;
    private final AutovehiculService autovehiculService;
    private final LocatieService locatieService;
    private final ServiciuExtraService serviciuService;

    public ContractInchiriereController(ContractInchiriereService service,
                                        ClientService clientService,
                                        AutovehiculService autovehiculService,
                                        LocatieService locatieService,
                                        ServiciuExtraService serviciuService) {
        this.service = service;
        this.clientService = clientService;
        this.autovehiculService = autovehiculService;
        this.locatieService = locatieService;
        this.serviciuService = serviciuService;
    }

    @ModelAttribute("clienti")
    public List<Client> clienti() { return clientService.findAll(); }

    @ModelAttribute("autovehicule")
    public List<Autovehicul> autovehicule() { return autovehiculService.findAll(); }

    @ModelAttribute("locatii")
    public List<Locatie> locatii() { return locatieService.findAll(); }

    @ModelAttribute("toateServiciile")
    public List<ServiciuExtra> servicii() { return serviciuService.findAll(); }

    @GetMapping
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", service.findAll(pageable));
        return "contracte/list";
    }

    @GetMapping("/nou")
    public String formNou(Model model) {
        model.addAttribute("contract", new ContractInchiriere());
        return "contracte/form";
    }

    @GetMapping("/{id}/editare")
    public String formEditare(@PathVariable Long id, Model model) {
        model.addAttribute("contract", service.findById(id));
        return "contracte/form";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("contract") ContractInchiriere contract, BindingResult result) {
        if (result.hasErrors()) {
            return "contracte/form";
        }
        service.save(contract);
        return "redirect:/contracte";
    }

    @PostMapping("/{id}/sterge")
    public String sterge(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/contracte";
    }
}
