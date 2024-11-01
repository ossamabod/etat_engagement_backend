package ma.fst.etatdengagement.Adapter;

import lombok.Builder;
import lombok.Data;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Indemnite.IndemniteDto;
import ma.fst.etatdengagement.DTO.Retenue.RetenueDto;
import ma.fst.etatdengagement.Models.Grade;
import org.springframework.stereotype.Component;

@Builder
@Data
@Component
public class GradeAdapter {
    public GradeDto fromGradeToGradeDto(Grade grade){
        return new GradeDto(grade.getId(),grade.getCode(), grade.getLibelle(), grade.getNiveau(), grade.getTraitementBase(),grade.getIndemnites()!=null ?new IndemniteDto(grade.getIndemnites().getId(),grade.getIndemnites().getIndemniteSujection(),grade.getIndemnites().getIndemniteFonction(),grade.getIndemnites().getIndemniteTournee(),grade.getIndemnites().getIndemniteRepresentation()):null,grade.getRetenue()!=null?new RetenueDto(grade.getRetenue().getId(),grade.getRetenue().getRCAR(),grade.getRetenue().getIGR(),grade.getRetenue().getAMO(),grade.getRetenue().getSM()):null);
    }
}
