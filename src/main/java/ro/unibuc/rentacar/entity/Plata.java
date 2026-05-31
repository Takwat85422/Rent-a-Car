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
@Table(name = "plata")
@Getter @Setter @NoArgsConstructor
public class Plata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plata")
    private Long idPlata;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_contract", nullable = false)
    private ContractInchiriere contract;

    @CreationTimestamp
    @Column(name = "data_plata", nullable = false, updatable = false)
    private LocalDate dataPlata;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal suma;

    @NotBlank
    @Pattern(regexp = "cash|card|transfer")
    @Column(nullable = false, length = 20)
    private String metoda;

    @NotBlank
    @Pattern(regexp = "confirmata|in_asteptare|respinsa")
    @Column(nullable = false, length = 20)
    private String status = "confirmata";
}