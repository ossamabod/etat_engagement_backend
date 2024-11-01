package ma.fst.etatdengagement.DTO.EtatEngagement;

public record EtatEngagementDto(Long id,
        double montantPA,
         double montantTotal,
        // Calculés automatiquement
         double montantAnnuel,
         double allocationsFamiliales,
         double emolumentsBruts,
         double totalRetenues,
         double brutMensuel,
         double netAOrdonner) {
}
