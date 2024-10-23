package ma.fst.etatdengagement.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtatEngagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montantPA;
    private double montantTotal;
    // Calculés automatiquement
    private double montantAnnuel;
    private double allocationsFamiliales;
    private double emolumentsBruts;
    private double totalRetenues;
    private double brutMensuel;
    private double netAOrdonner;
    @ManyToOne
    @JsonBackReference
    private Employee employee;
}