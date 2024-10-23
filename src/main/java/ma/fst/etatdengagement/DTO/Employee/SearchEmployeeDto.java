package ma.fst.etatdengagement.DTO.Employee;

import lombok.*;
import ma.fst.etatdengagement.DTO.BaseSearchDTO;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeDto extends BaseSearchDTO {
    private Long employeeId;
    private String cin;
    private String prenom;
    private String nom;

}