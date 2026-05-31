package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "permis_conducere")
@Getter @Setter @NoArgsConstructor
public class PermisConducere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permis")
    private Long idPermis;

    @NotBlank @Size(max = 20)
    @Column(name = "numar_permis", nullable = false, unique = true)
    private String numarPermis;

    @NotBlank
    @Pattern(regexp = "AM|A1|A2|A|B|BE|C|CE|D|DE")
    @Column(name = "categorie_principala", nullable = false, length = 5)
    private String categoriePrincipala;

    @NotBlank @Size(max = 50)
    @Column(name = "tara_emitenta", nullable = false)
    private String taraEmitenta = "Romania";

    @NotNull
    @Column(name = "data_emitere", nullable = false)
    private LocalDate dataEmitere;

    @NotNull
    @Column(name = "data_expirare", nullable = false)
    private LocalDate dataExpirare;

    // 1:1 - partea proprietara (cheia straina id_client, unica)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false, unique = true)
    private Client client;
}