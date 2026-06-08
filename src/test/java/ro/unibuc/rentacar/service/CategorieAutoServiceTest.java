package ro.unibuc.rentacar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.rentacar.entity.CategorieAuto;
import ro.unibuc.rentacar.repository.CategorieAutoRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategorieAutoServiceTest {

    @Mock
    private CategorieAutoRepository repo;
    @InjectMocks
    private CategorieAutoService service;

    @Test
    void findById_existent_returneazaCategoria() {
        CategorieAuto c = new CategorieAuto();
        c.setIdCategorie(1L);
        c.setDenumire("Economic");
        when(repo.findById(1L)).thenReturn(Optional.of(c));

        assertThat(service.findById(1L).getDenumire()).isEqualTo("Economic");
    }

    @Test
    void findById_inexistent_aruncaExceptie() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("inexistenta");
    }
}