package ma.fst.etatdengagement.Models;

import lombok.*;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Retenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double RCAR;
    private double IGR;
    private double AMO;
    private double SM;

    // Owning side of the One-to-One relationship with Grade
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
    @JsonIgnore  // Add this to break the cycle
    private Grade grade;

}
