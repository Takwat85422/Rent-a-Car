package ro.unibuc.rentacar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ro.unibuc.rentacar.entity.CategorieAuto;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CategorieAutoRepositoryTest {

    @Autowired
    private CategorieAutoRepository repo;

    @Test
    void salveazaSiGasesteCategorie() {
        CategorieAuto c = new CategorieAuto();
        c.setDenumire("SUV");
        c.setCategoriePermisNecesara("B");
        c.setTarifZi(new BigDecimal("220.00"));

        CategorieAuto salvat = repo.save(c);

        assertThat(salvat.getIdCategorie()).isNotNull();
        Optional<CategorieAuto> gasit = repo.findById(salvat.getIdCategorie());
        assertThat(gasit).isPresent();
        assertThat(gasit.get().getDenumire()).isEqualTo("SUV");
    }
}