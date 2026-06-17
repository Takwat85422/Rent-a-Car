package ro.unibuc.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.unibuc.rentacar.repository.AutovehiculRepository;
import ro.unibuc.rentacar.repository.ClientRepository;
import ro.unibuc.rentacar.repository.ContractInchiriereRepository;
import ro.unibuc.rentacar.repository.DaunaRepository;
import ro.unibuc.rentacar.repository.PlataRepository;

@Controller
public class HomeController {

    private final ClientRepository clientRepo;
    private final AutovehiculRepository autovehiculRepo;
    private final ContractInchiriereRepository contractRepo;
    private final PlataRepository plataRepo;
    private final DaunaRepository daunaRepo;

    public HomeController(ClientRepository clientRepo,
                          AutovehiculRepository autovehiculRepo,
                          ContractInchiriereRepository contractRepo,
                          PlataRepository plataRepo,
                          DaunaRepository daunaRepo) {
        this.clientRepo = clientRepo;
        this.autovehiculRepo = autovehiculRepo;
        this.contractRepo = contractRepo;
        this.plataRepo = plataRepo;
        this.daunaRepo = daunaRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("nrClienti", clientRepo.count());
        model.addAttribute("nrAutovehicule", autovehiculRepo.count());
        model.addAttribute("nrContracte", contractRepo.count());
        model.addAttribute("nrPlati", plataRepo.count());
        model.addAttribute("nrDaune", daunaRepo.count());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/acces-interzis")
    public String accesInterzis(Model model) {
        model.addAttribute("titlu", "Acces interzis");
        model.addAttribute("mesaj", "Nu ai drepturile necesare. Doar un ADMIN poate face aceasta actiune.");
        return "eroare";
    }
}
