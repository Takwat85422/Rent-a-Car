package ro.unibuc.rentacar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.rentacar.entity.Autovehicul;
import ro.unibuc.rentacar.entity.ContractInchiriere;
import ro.unibuc.rentacar.entity.ServiciuExtra;
import ro.unibuc.rentacar.repository.AutovehiculRepository;
import ro.unibuc.rentacar.repository.ContractInchiriereRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractInchiriereService {

    private final ContractInchiriereRepository repo;
    private final AutovehiculRepository autoRepo;

    public ContractInchiriereService(ContractInchiriereRepository repo,
                                     AutovehiculRepository autoRepo) {
        this.repo = repo;
        this.autoRepo = autoRepo;
    }

    public List<ContractInchiriere> findAll() {
        return repo.findAll();
    }

    public ContractInchiriere findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contract inexistent: " + id));
    }

    @Transactional
    public void save(ContractInchiriere contract) {
        // reincarcam masina in tranzactie ca sa putem accesa categoria (relatie LAZY)
        Autovehicul masina = autoRepo.findById(contract.getAutovehicul().getIdAutovehicul())
                .orElseThrow(() -> new IllegalArgumentException("Autovehicul inexistent"));

        // km start = kilometrajul curent al masinii
        contract.setKmStart(masina.getKmCurenti());

        // tarif/zi = tariful categoriei + suma preturilor serviciilor selectate
        BigDecimal tarif = masina.getCategorie().getTarifZi();
        if (contract.getServicii() != null) {
            for (ServiciuExtra s : contract.getServicii()) {
                tarif = tarif.add(s.getPretZi());
            }
        }
        contract.setTarifZi(tarif);

        repo.save(contract);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}