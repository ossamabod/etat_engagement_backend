package ma.fst.etatdengagement.DTO.Grade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import ma.fst.etatdengagement.DTO.Indemnite.IndemniteDto;
import ma.fst.etatdengagement.DTO.Retenue.RetenueDto;
import ma.fst.etatdengagement.Models.Employee;
import ma.fst.etatdengagement.Models.Indemnites;
import ma.fst.etatdengagement.Models.Retenue;

import java.util.List;

public record GradeDto( Long id,
         String code,
         String libelle,
         String niveau,
         float traitementBase,
         IndemniteDto indemnites,
         RetenueDto Retenue
) {
}
