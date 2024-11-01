package ma.fst.etatdengagement.DTO.Enfant;

import java.util.Date;

public record EnfantDto( Long id,
         String nom,
         String prenom,
         Date dateNaissance) {
}
