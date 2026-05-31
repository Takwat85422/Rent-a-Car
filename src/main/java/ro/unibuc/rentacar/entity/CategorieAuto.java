
package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie_auto")
@Getter
@Setter
@NoArgsConstructor
public class CategorieAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private Long idCategorie;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String denumire;

    @Size(max = 200)
    private String descriere;

    @NotBlank
    @Pattern(regexp = "AM|A1|A2|A|B|BE|C|CE|D|DE", message = "Categorie permis invalida")
    @Column(name = "categorie_permis_necesara", nullable = false, length = 5)
    private String categoriePermisNecesara;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "tarif_zi", nullable = false, precision = 8, scale = 2)
    private BigDecimal tarifZi;


    @OneToMany(mappedBy = "categorie")
    private List<Autovehicul> autovehicule = new ArrayList<>();
}