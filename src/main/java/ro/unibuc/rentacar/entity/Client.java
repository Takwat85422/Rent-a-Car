package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter @Setter @NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @NotBlank @Size(max = 50)
    @Column(nullable = false)
    private String nume;

    @NotBlank @Size(max = 50)
    @Column(nullable = false)
    private String prenume;

    @NotBlank
    @Pattern(regexp = "^[0-9]{13}$", message = "CNP-ul trebuie sa aiba 13 cifre")
    @Column(nullable = false, unique = true, length = 13)
    private String cnp;

    @NotNull @Past
    @Column(name = "data_nasterii", nullable = false)
    private LocalDate dataNasterii;

    @Email @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotBlank @Size(max = 15)
    @Column(nullable = false)
    private String telefon;

    @Size(max = 200)
    private String adresa;

    @CreationTimestamp
    @Column(name = "data_inregistrare", nullable = false, updatable = false)
    private LocalDate dataInregistrare;

    @NotBlank
    @Pattern(regexp = "activ|inactiv|blocat")
    @Column(name = "status_client", nullable = false, length = 20)
    private String statusClient = "activ";

    // 1:1 cu permisul (partea inversa)
    @OneToOne(mappedBy = "client")
    private PermisConducere permis;

    // 1:N cu contractele
    @OneToMany(mappedBy = "client")
    private List<ContractInchiriere> contracte = new ArrayList<>();
}