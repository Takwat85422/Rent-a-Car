package ro.unibuc.rentacar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "locatie",
        uniqueConstraints = @UniqueConstraint(columnNames = {"oras", "adresa"}))
@Getter @Setter @NoArgsConstructor
public class Locatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locatie")
    private Long idLocatie;

    @NotBlank @Size(max = 50)
    @Column(nullable = false)
    private String oras;

    @NotBlank @Size(max = 200)
    @Column(nullable = false)
    private String adresa;

    @NotBlank
    @Pattern(regexp = "sediu|punct")
    @Column(nullable = false, length = 20)
    private String tip = "punct";

    @Size(max = 100)
    private String program;
}