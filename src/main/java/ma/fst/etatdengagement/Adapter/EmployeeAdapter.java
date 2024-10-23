package ma.fst.etatdengagement.Adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.Models.Employee;
import ma.fst.etatdengagement.Services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Builder
@Data
@Component
public class EmployeeAdapter {
    private ModelMapper modelMapper;
    public EmployeeDto fromEmployeeToEmployeeDTO(Employee employee){
            return new EmployeeDto( employee.getEmployeeId(),
                    employee.getCin(),
                    employee.getNom(),
                    employee.getPrenom(),
                    employee.getDateNaissance(),
                    employee.getLieuNaissance(),
                    employee.getSexe(),
                    employee.getAdresse(),
                    employee.getVille(),
                    employee.getDateEntree(),
                    employee.getDateRecrut(),
                    employee.getAvancement(),
                    employee.getDiplome(),
                    employee.getAffectation(),
                    employee.getNivInst(),
                    employee.getSituationFam(),
                    employee.getNbEnfants(),
                    employee.getAdresseFam(),
                    employee.getTel(),
                    employee.getObservations(),
                    employee.getAge(),
                    employee.getBanque(),
                    employee.getNumeroCompte(),
                    employee.getBudget(),
                    employee.getDp(),
                    employee.getProvince(),
                    employee.getRegion(),
                    employee.getPa());
        }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
