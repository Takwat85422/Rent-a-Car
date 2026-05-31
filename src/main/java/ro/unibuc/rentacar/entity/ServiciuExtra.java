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
@Table(name = "serviciu_extra")
@Getter @Setter @NoArgsConstructor
public class ServiciuExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_serviciu")
    private Long idServiciu;

    @NotBlank @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String denumire;

    @Size(max = 200)
    private String descriere;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "pret_zi", nullable = false, precision = 8, scale = 2)
    private BigDecimal pretZi;

    @NotBlank
    @Pattern(regexp = "DA|NU")
    @Column(nullable = false, length = 2)
    private String activ = "DA";

    // M:N - partea inversa (proprietara e ContractInchiriere)
    @ManyToMany(mappedBy = "servicii")
    private List<ContractInchiriere> contracte = new ArrayList<>();
}