package ma.fst.etatdengagement.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String cin;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String lieuNaissance;
    private String sexe;
    private String adresse;
    private String ville;
    private Date dateEntree;
    private Date dateRecrut;
    private String avancement;
    private String diplome;
    private String affectation;
    private String nivInst;
    private String situationFam;
    private Integer nbEnfants;
    private String adresseFam;
    private String tel;
    private String observations;
    private Integer age;
    private String banque;
    private String numeroCompte;
    private String budget;
    private String dp;
    private String province;
    private String region;
    private double Pa;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;  // This represents the selected grade for the employee

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Conjoint conjoint;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference // Used to manage circular reference with Enfant
    private List<Enfant> enfants;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EtatEngagement> EtatEngagement ;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", sexe='" + sexe + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", dateEntree=" + dateEntree +
                ", dateRecrut=" + dateRecrut +
                ", avancement='" + avancement + '\'' +
                ", diplome='" + diplome + '\'' +
                ", affectation='" + affectation + '\'' +
                ", nivInst='" + nivInst + '\'' +
                ", situationFam='" + situationFam + '\'' +
                ", nbEnfants=" + nbEnfants +
                ", adresseFam='" + adresseFam + '\'' +
                ", tel='" + tel + '\'' +
                ", observations='" + observations + '\'' +
                ", age=" + age +
                ", banque='" + banque + '\'' +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", budget='" + budget + '\'' +
                ", dp='" + dp + '\'' +
                ", province='" + province + '\'' +
                ", region='" + region + '\'' +
                ", Pa=" + Pa +
                '}';
    }

}
