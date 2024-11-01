package ma.fst.etatdengagement.DTO.Employee;

import ma.fst.etatdengagement.DTO.Conjoint.ConjointDto;
import ma.fst.etatdengagement.DTO.Enfant.EnfantDto;
import ma.fst.etatdengagement.DTO.EtatEngagement.EtatEngagementDto;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;

import java.util.Date;
import java.util.List;

public record EmployeeDto( Long employeeId,String cin,
         String nom,
         String prenom,
         Date dateNaissance,
         String lieuNaissance,
         String sexe,
         String adresse,
         String ville,
         Date dateEntree,
         Date dateRecrut,
         String avancement,
         String diplome,
         String affectation,
         String nivInst,
         String situationFam,
         Integer nbEnfants,
         String adresseFam,
         String tel,
         String observations,
         Integer age,
         String banque,
         String numeroCompte,
         String budget,
         String dp,
         String province,
         String region,
         double Pa,
         GradeDto grade,
         ConjointDto conjoint,
         List<EnfantDto> enfants,
        List<EtatEngagementDto> EtatEngagement
) {

}
