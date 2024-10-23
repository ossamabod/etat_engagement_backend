package ma.fst.etatdengagement.DTO.Employee;

import java.util.Date;

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
         double Pa
) {
}
