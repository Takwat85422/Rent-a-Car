package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dauna")
@Getter @Setter @NoArgsConstructor
public class Dauna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dauna")
    private Long idDauna;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_contract", nullable = false)
    private ContractInchiriere contract;

    @CreationTimestamp
    @Column(name = "data_constatare", nullable = false, updatable = false)
    private LocalDate dataConstatare;

    @NotBlank @Size(max = 300)
    @Column(nullable = false, length = 300)
    private String descriere;

    @NotNull @DecimalMin("0.0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost = BigDecimal.ZERO;

    @NotBlank
    @Pattern(regexp = "deschisa|inchisa|anulata")
    @Column(nullable = false, length = 20)
    private String status = "deschisa";

    @NotBlank
    @Pattern(regexp = "client|firma|necunoscut")
    @Column(nullable = false, length = 20)
    private String responsabil = "client";
}