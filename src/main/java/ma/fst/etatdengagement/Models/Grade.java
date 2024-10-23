package ma.fst.etatdengagement.Models;

import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String libelle;
    private String niveau;
    private Float traitementBase;


    @OneToMany
    private List<Employee> employee;

    // One-to-One relationship with Indemnites
    @OneToOne(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private Indemnites indemnites;

    // One-to-One relationship with Retenue
    @OneToOne(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private Retenue retenue;

}
