package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract_inchiriere")
@Getter @Setter @NoArgsConstructor
public class ContractInchiriere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private Long idContract;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_autovehicul", nullable = false)
    private Autovehicul autovehicul;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_locatie_ridicare", nullable = false)
    private Locatie locatieRidicare;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_locatie_predare", nullable = false)
    private Locatie locatiePredare;

    @NotNull
    @Column(name = "data_start", nullable = false)
    private LocalDate dataStart;

    @NotNull
    @Column(name = "data_sfarsit", nullable = false)
    private LocalDate dataSfarsit;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "tarif_zi", nullable = false, precision = 8, scale = 2)
    private BigDecimal tarifZi;

    @NotNull
    @DecimalMin("0.0")
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal garantie = BigDecimal.ZERO;

    @NotBlank
    @Pattern(regexp = "activ|inchis|anulat")
    @Column(nullable = false, length = 20)
    private String status = "activ";

    @NotNull @PositiveOrZero
    @Column(name = "km_start", nullable = false)
    private Long kmStart = 0L;

    @PositiveOrZero
    @Column(name = "km_return")
    private Long kmReturn;

    @CreationTimestamp
    @Column(name = "data_creare", nullable = false, updatable = false)
    private LocalDate dataCreare;

    @OneToMany(mappedBy = "contract")
    private List<Plata> plati = new ArrayList<>();

    @OneToMany(mappedBy = "contract")
    private List<Dauna> daune = new ArrayList<>();

    // M:N cu serviciile extra (tabel de legatura contract_serviciu)
    @ManyToMany
    @JoinTable(name = "contract_serviciu",
            joinColumns = @JoinColumn(name = "id_contract"),
            inverseJoinColumns = @JoinColumn(name = "id_serviciu"))
    private List<ServiciuExtra> servicii = new ArrayList<>();
}