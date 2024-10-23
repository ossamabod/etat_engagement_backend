package ma.fst.etatdengagement.Adapter;

import lombok.Builder;
import lombok.Data;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.Models.Grade;
import org.springframework.stereotype.Component;

@Builder
@Data
@Component
public class GradeAdapter {
    public GradeDto fromGradeToGradeDto(Grade grade){
        return new GradeDto(grade.getId(),grade.getCode(), grade.getLibelle(), grade.getNiveau(), grade.getTraitementBase());
    }
}