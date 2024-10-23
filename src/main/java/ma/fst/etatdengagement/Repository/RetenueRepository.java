package ma.fst.etatdengagement.Repository;

import ma.fst.etatdengagement.Models.Retenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetenueRepository extends JpaRepository<Retenue, Long> {
}
