package ma.fst.etatdengagement.Adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fst.etatdengagement.DTO.Conjoint.ConjointDto;
import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Enfant.EnfantDto;
import ma.fst.etatdengagement.DTO.EtatEngagement.EtatEngagementDto;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Indemnite.IndemniteDto;
import ma.fst.etatdengagement.DTO.Retenue.RetenueDto;
import ma.fst.etatdengagement.Models.Employee;
import ma.fst.etatdengagement.Services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
                    employee.getPa(),
                    employee.getGrade() != null ? new GradeDto(employee.getGrade().getId(), employee.getGrade().getCode(),employee.getGrade().getLibelle(),employee.getGrade().getNiveau(),employee.getGrade().getTraitementBase(),employee.getGrade().getIndemnites()!=null ?new IndemniteDto(employee.getGrade().getIndemnites().getId(),employee.getGrade().getIndemnites().getIndemniteSujection(),employee.getGrade().getIndemnites().getIndemniteFonction(),employee.getGrade().getIndemnites().getIndemniteTournee(),employee.getGrade().getIndemnites().getIndemniteRepresentation()):null,employee.getGrade().getRetenue()!=null?new RetenueDto(employee.getGrade().getRetenue().getId(),employee.getGrade().getRetenue().getRCAR(),employee.getGrade().getRetenue().getIGR(),employee.getGrade().getRetenue().getAMO(),employee.getGrade().getRetenue().getSM()):null) : null,
                    employee.getConjoint() != null ? new ConjointDto(employee.getConjoint().getId(), employee.getConjoint().getNom(), employee.getConjoint().getPrenom(), employee.getConjoint().getDateNaissance(),employee.getConjoint().getProfession()) : null,
                    employee.getEnfants() != null ? employee.getEnfants().stream()
                            .map(enfant -> new EnfantDto(enfant.getId(), enfant.getNom(),enfant.getPrenom(), enfant.getDateNaissance()))
                            .collect(Collectors.toList()) : null,
                    employee.getEtatEngagement() != null ? employee.getEtatEngagement().stream()
                            .map(etat -> new EtatEngagementDto(etat.getId(),etat.getMontantPA(), etat.getMontantTotal(), etat.getMontantAnnuel(), etat.getAllocationsFamiliales(), etat.getBrutMensuel(), etat.getEmolumentsBruts(), etat.getNetAOrdonner(),etat.getTotalRetenues()))
                            .collect(Collectors.toList()) : null);
        }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
