package ma.fst.etatdengagement.DTO.Conjoint;

import java.util.Date;

public record ConjointDto( Long id,
         String nom,
         String prenom,
         Date dateNaissance,
         String profession
) {
}
