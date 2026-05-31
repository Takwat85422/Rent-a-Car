package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "autovehicul")
@Getter
@Setter
@NoArgsConstructor
public class Autovehicul {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autovehicul")
    private Long idAutovehicul;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String marca;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String model;

    @NotNull
    @Min(1980)
    @Max(2100)
    @Column(name = "an_fabricatie", nullable = false)
    private Integer anFabricatie;

    @NotBlank
    @Size(max = 15)
    @Column(name = "nr_inmatriculare", nullable = false, unique = true)
    private String nrInmatriculare;

    @NotBlank
    @Size(min = 17, max = 17, message = "VIN-ul are exact 17 caractere")
    @Column(nullable = false, unique = true, length = 17)
    private String vin;

    @Size(max = 30)
    private String culoare;

    @NotBlank
    @Pattern(regexp = "benzina|motorina|hibrid|electric|gpl")
    @Column(nullable = false, length = 20)
    private String combustibil;

    @NotBlank
    @Pattern(regexp = "manuala|automata")
    @Column(nullable = false, length = 20)
    private String transmisie;

    @NotNull
    @PositiveOrZero
    @Column(name = "km_curenti", nullable = false)
    private Long kmCurenti = 0L;

    @NotBlank
    @Pattern(regexp = "disponibil|inchiriat|mentenanta|indisponibil")
    @Column(nullable = false, length = 20)
    private String stare = "disponibil";

    @Column(name = "data_adaugare", nullable = false, updatable = false)
    private LocalDate dataAdaugare;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categorie", nullable = false)
    private CategorieAuto categorie;

    @PrePersist
    void prePersist() {
        if (dataAdaugare == null) {
            dataAdaugare = LocalDate.now();
        }
    }
}
