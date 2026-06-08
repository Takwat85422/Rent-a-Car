package ro.unibuc.rentacar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.rentacar.entity.*;
import ro.unibuc.rentacar.repository.AutovehiculRepository;
import ro.unibuc.rentacar.repository.ContractInchiriereRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractInchiriereServiceTest {

    @Mock
    private ContractInchiriereRepository contractRepo;
    @Mock
    private AutovehiculRepository autoRepo;
    @InjectMocks
    private ContractInchiriereService service;

    @Test
    void save_calculeazaTarifulSiKmStart() {
        CategorieAuto categorie = new CategorieAuto();
        categorie.setTarifZi(new BigDecimal("100.00"));

        Autovehicul masina = new Autovehicul();
        masina.setIdAutovehicul(1L);
        masina.setKmCurenti(5000L);
        masina.setCategorie(categorie);

        ServiciuExtra gps = new ServiciuExtra();
        gps.setPretZi(new BigDecimal("15.00"));
        ServiciuExtra sofer = new ServiciuExtra();
        sofer.setPretZi(new BigDecimal("20.00"));

        Autovehicul refMasina = new Autovehicul();
        refMasina.setIdAutovehicul(1L);

        ContractInchiriere contract = new ContractInchiriere();
        contract.setAutovehicul(refMasina);
        contract.setServicii(List.of(gps, sofer));

        when(autoRepo.findById(1L)).thenReturn(Optional.of(masina));

        service.save(contract);

        // tarif = 100 (categorie) + 15 + 20 (servicii) = 135 ; km start = km masinii
        assertThat(contract.getTarifZi()).isEqualByComparingTo("135.00");
        assertThat(contract.getKmStart()).isEqualTo(5000L);
        verify(contractRepo).save(contract);
    }
}