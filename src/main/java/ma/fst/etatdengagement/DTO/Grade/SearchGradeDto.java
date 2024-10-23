package ma.fst.etatdengagement.DTO.Grade;

import lombok.*;
import ma.fst.etatdengagement.DTO.BaseSearchDTO;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchGradeDto extends BaseSearchDTO {
    private Long gradeid;
    private String code;
    private String libelle;
    private String niveau;
    private float traitementBase;



}
