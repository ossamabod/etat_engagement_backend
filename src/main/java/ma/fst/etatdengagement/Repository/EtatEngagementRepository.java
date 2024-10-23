package ma.fst.etatdengagement.Repository;

import ma.fst.etatdengagement.Models.EtatEngagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatEngagementRepository extends JpaRepository<EtatEngagement, Long> {
}
